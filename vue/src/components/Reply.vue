<template>
  <div>
    <div v-for="reply in replies" v-bind:key="reply.id" class="margin-top">
      <div>
        <h3>{{ reply.title }}</h3>
        <span>Post by: {{ reply.username }} </span>
        <span>posted on: {{ reply.date }}</span>
        <p>{{ reply.reply }}</p>
        <button
          v-if="isUser(reply.userId) || isAdmin()"
          class="margin-right btn btn-primary"
          v-on:click="updateReply(reply.id)"
        >
          Update Reply
        </button>
        <button
          v-if="isUser(reply.userId) || isAdmin()"
          class="btn btn-danger"
          v-on:click="deleteReply(reply.id)"
        >
          Delete Reply
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import forumService from "../services/ForumService.js";
export default {
  data() {
    return {
      replies: [],
    };
  },
  created() {
    forumService.getReplyByPostId(this.$route.params.id).then((response) => {
      this.replies = response.data;
    });
  },
  methods: {
    isAdmin() {
      if (this.$store.state.logIn) {
        if (this.$store.state.user.authorities[0].name === "ROLE_ADMIN") {
          return true;
        }
      } else {
        return false;
      }
    },
    isUser(id) {
      if (this.$store.state.logIn) {
        if (this.$store.state.user.id == id) {
          return true;
        }
      } else {
        return false;
      }
    },
    updateReply(id) {
      this.$router.push({name: "updateReply", params: {postId: this.$route.params.postId, id: id}})
    },
    deleteReply(id) {
      forumService.deleteReply(id).then(() => {
        this.$router.push({name: "forums"})
      })
    }
  },
};
</script>

<style scoped>
.margin-right {
  margin-right: 15px;
}
.margin-top {
  margin-top: 15px;
}
</style>