<template>
  <div>
    <section class="page-section cta">
      <div class="container">
        <div class="row">
          <div class="col-xl-12 mx-auto">
            <div class="cta-inner rounded">
              <BeerForm />
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import BeerForm from "../components/BeerForm.vue"
export default {
    components: {
        BeerForm,
    }
};
</script>

<style>
</style>