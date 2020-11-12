<template>
  <div>
    <section class="page-section cta">
      <div class="container">
        <div class="row">
          <div class="col-xl-12 mx-auto">
            <div class="cta-inner rounded">
              <div id="post" class="border border-dark">
                <h3 class="margin-side">{{ post.title }}</h3>
                <span class="margin-side">Post by: {{ post.username }} </span>
                <span>posted on: {{ post.date }}</span>
                <p class="margin-side">{{ post.message }}</p>
                <div class="postImage">
                  <img v-bind:src="post.imageUrl" alt="" />
                </div>
                <button
                  v-if="
                    (this.post.userId == this.$store.state.user.id ||
                      isAdmin()) &&
                    this.$store.state.logIn
                  "
                  class="margin-side btn btn-primary"
                  v-on:click="updatePost"
                >
                  Update Post
                </button>
                <button
                  v-if="
                    (this.post.userId == this.$store.state.user.id ||
                      isAdmin()) &&
                    this.$store.state.logIn
                  "
                  class="btn btn-danger"
                  v-on:click="deletePost"
                >
                  Delete Post
                </button>
              </div>
              <button
                v-on:click="addReply"
                id="reply"
                class="margin-side btn btn-primary"
              >
                Reply
              </button>
              <Reply />
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import forumServices from "../services/ForumService.js";
import Reply from "./Reply.vue";
export default {
  data() {
    return {
      post: {},
    };
  },
  components: {
    Reply,
  },
  created() {
    forumServices.getPostById(this.$route.params.id).then((response) => {
      this.post = response.data;
    });
  },
  methods: {
    updatePost() {
      this.$router.push({
        name: "updatePost",
        params: { id: this.$route.params.id },
      });
    },
    deletePost() {
      forumServices.deletePost(this.$route.params.id).then(() => {
        this.$router.push({ name: "forums" });
      });
    },
    isAdmin() {
      if (this.$store.state.logIn) {
        if (this.$store.state.user.authorities[0].name === "ROLE_ADMIN") {
          return true;
        }
      } else {
        return false;
      }
    },
    addReply() {
      if (this.$store.state.logIn) {
        this.$router.push({
          name: "addReply",
          params: { postId: this.$route.params.id },
        });
      } else {
        this.$router.push({ name: "login" });
      }
    },
  },
};
</script>

<style scoped>
.postImage {
  display: flex;
  margin-bottom: 25px;
  justify-content: space-around;
}
img {
  max-width: 90%;
}
button {
  margin-bottom: 25px;
}
.margin-side {
  margin-left: 15px;
  margin-right: 15px;
}
#reply {
  margin-top: 25px;
  margin-bottom: 25px;
}
</style>