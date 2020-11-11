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
                    placeholder="Enter post title"
                    v-model="post.title"
                  />
                </div>
                <div>
                  <label for="message">Message</label>
                  <textarea
                    class="form-control"
                    id="message"
                    rows="5"
                    placeholder="Message text"
                    v-model="post.message"
                  ></textarea>
                </div>
                <div class="d-flex-inline">
                  <label for="image">Image URL</label>
                  <input
                    type="text"
                    class="form-control"
                    id="image"
                    placeholder="Enter image URL"
                    v-model="post.imageUrl"
                  />
                </div>
                <div>
                  <br />
                  <button
                    v-if="this.$route.params.id == null"
                    id="addPost"
                    type="submit"
                    class="btn btn-primary btn-sm"
                    v-on:click.prevent="newPost"
                  >
                    Add Post
                  </button>
                  <button
                    v-else
                    id="updatePost"
                    type="submin"
                    class="btn btn-primary btn-sm"
                    v-on:click.prevent="updatePost"
                  >
                    Update Beer
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
      post: {
        userId: this.$store.state.user.id,
        stickied: false,
      },
    };
  },
  methods: {
    newPost() {
      this.post.date = new Date();
      forumService.addPost(this.post).then(() => {
        this.$router.push({ name: "forums" });
      });
    },
    updatePost() {
      forumService.updatePost(this.post).then(() => {
        this.$router.push({name: "postDetails", params: {id: this.$route.params.id}})
      })
    }
  },
  created() {
    if (this.$route.params.id != null) {
      forumService.getPostById(this.$route.params.id).then((response) => {
        this.post = response.data;
      });
    }
  },
};
</script>

<style>
</style>