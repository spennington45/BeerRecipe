<template>
  <div>
    <section class="page-section cta">
      <div class="container">
        <div class="row">
          <div class="col-xl-12 mx-auto">
            <div class="cta-inner rounded">
              <form>
                <div class="flex-grow-1 margin-bottom">
                  <h3>
                    <label for="name">Filter By Post Title</label>
                  </h3>
                  <input
                    type="text"
                    class="form-control"
                    id="search"
                    placeholder="Enter Post Title"
                    v-model="filterText"
                  />
                </div>
              </form>
              <div class="flex-grow-1">
                  <h3>
                    <label for="name">Create A New Post</label>
                  </h3>
                  <button
                    type="button"
                    class="btn btn-primary"
                    id="newPost"
                    v-on:click="newPost()"
                  >New Post</button>
                </div>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-xl-12 mx-auto">
            <div class="cta-inner bg-faded p-5 rounded">
              <div
                v-for="post in stickied"
                v-bind:key="post.id"
                class="margin-bottom"
              >
                <div class="border border-dark">
                  <h4>
                    <router-link
                      class="nav-link"
                      v-bind:to="{
                        name: 'postDetails',
                        params: { id: post.id },
                      }"
                    >
                      {{ post.title }}
                    </router-link>
                  </h4>
                  <div class="post-text">
                    <span
                      >Posted by: {{ post.username }} on {{ post.date }}</span
                    >
                    <div>
                      <span class="text-success" v-if="post.stickied"
                        >Stickied
                      </span>
                      <span
                        class="text-success span-btn"
                        v-if="!post.stickied && isAdmin()"
                        v-on:click="stickyToggle(post)"
                        >Sticky Post</span
                      >
                      <span
                        v-if="post.stickied && isAdmin()"
                        class="text-success span-btn"
                        v-on:click="stickyToggle(post)"
                        >Unsticky Post</span
                      >
                    </div>
                  </div>
                </div>
              </div>
              <div
                v-for="post in notStickied"
                v-bind:key="post.id"
                class="margin-bottom"
              >
                <div class="border border-dark">
                  <h4>
                    <router-link
                      class="nav-link"
                      v-bind:to="{
                        name: 'postDetails',
                        params: { id: post.id },
                      }"
                    >
                      {{ post.title }}
                    </router-link>
                  </h4>
                  <div class="post-text">
                    <span
                      >Posted by: {{ post.username }} on {{ post.date }}</span
                    >
                    <div>
                      <span class="text-success" v-if="post.stickied"
                        >Stickied
                      </span>
                      <span
                        class="text-success span-btn"
                        v-if="!post.stickied && isAdmin()"
                        v-on:click="stickyToggle(post)"
                        >Sticky Post</span
                      >
                      <span
                        v-if="post.stickied && isAdmin()"
                        class="text-success span-btn"
                        v-on:click="stickyToggle(post)"
                        >Unsticky Post</span
                      >
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import forumService from "../services/ForumService";
export default {
  data() {
    return {
      posts: [],
      filterText: "",
    };
  },
  created() {
    forumService.getAllPost().then((response) => {
      this.posts = response.data;
    });
  },
  computed: {
    filteredList() {
      return this.posts.filter((post) => {
        return post.title.toLowerCase().includes(this.filterText.toLowerCase());
      });
    },
    stickied() {
      return this.filteredList.filter((post) => {
        return post.stickied == true;
      });
    },
    notStickied() {
      return this.filteredList.filter((post) => {
        return post.stickied == false;
      });
    },
  },
  methods: {
    stickyToggle(post) {
      if (post.stickied) {
        forumService.unsticky(post.id);
      } else {
        forumService.sticky(post.id);
      }
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
    newPost() {
      if (this.$store.state.logIn) {
        this.$router.push({name: "postForm"})
      } else {
        this.$router.push({name: "login"})
      }
    },
  },
};
</script>

<style scoped>
.post-text {
  display: flex;
  justify-content: space-between;
  padding-left: 10px;
  padding-right: 10px;
}
.span-btn:hover {
  cursor: pointer;
}
.margin-bottom {
  margin-bottom: 10px;
}
</style>