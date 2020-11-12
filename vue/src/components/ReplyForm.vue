<template>
  <div>
    <section class="page-section cta">
      <div class="container">
        <div class="row">
          <div class="col-xl-12 mx-auto">
            <div class="cta-inner rounded">
              <form class="flex-grow-1">
                <div>
                  <label for="title">Title</label>
                  <input
                    type="text"
                    class="form-control"
                    id="title"
                    placeholder="Enter reply title"
                    v-model="reply.title"
                  />
                </div>
                <div>
                  <label for="message">Message</label>
                  <textarea
                    class="form-control"
                    id="message"
                    rows="5"
                    placeholder="Message text"
                    v-model="reply.reply"
                  ></textarea>
                </div>
                <div>
                  <br />
                  <button
                    v-if="this.$route.params.id == null"
                    id="addReply"
                    type="submit"
                    class="btn btn-primary btn-sm"
                    v-on:click.prevent="newReply"
                  >
                    Add Reply
                  </button>
                  <button
                    v-else
                    id="updateReply"
                    type="submin"
                    class="btn btn-primary btn-sm"
                    v-on:click.prevent="updateReply"
                  >
                    Update Reply
                  </button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import forumService from "../services/ForumService.js";
export default {
  data() {
    return {
      reply: {
        userId: this.$store.state.user.id,
        postId: this.$route.params.postId,
      },
    };
  },
  methods: {
    newReply() {
      this.reply.date = new Date();
      forumService.addReply(this.reply).then(() => {
        this.$router.push({ name: "postDetails", params: {id: this.reply.postId} });
      });
    },
    updateReply() {
      forumService.updateReply(this.reply).then(() => {
        this.$router.push({name: "postDetails", params: {id: this.reply.postId}})
      })
    }
  },
  created() {
    if (this.$route.params.id != null) {
      forumService.getReplyById(this.$route.params.id).then((response) => {
        this.reply = response.data;
      });
    }
  },
};
</script>

<style>
</style>