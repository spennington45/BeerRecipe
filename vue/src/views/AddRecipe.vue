<template>
  <div>
      <section class="page-section cta">
    <div class="container">
      <div class="row">
        <div class="col-xl-12 mx-auto">
          <div class="cta-inner rounded">
            <form>
                <div class="flex-grow-1">
                  <label for="name">Title</label>
                  <input type="text" class="form-control" id="name" placeholder="Enter recipe name">
                </div>
                <div>
                  <label for="Ingredents">Ingredents</label>
                  <textarea class="form-control" id="ingredents" rows="5" placeholder="Ingredents"></textarea>
                </div>
                <div class="d-flex-inline">
                  <label for="Volume">Volume</label>
                  <input type="text" class="form-control" id="Volume" placeholder="Enter volume">
                  <label for="Units">Units</label>
                  <select class="form-control" id="Units">
                    <option>L</option>
                    <option>Gal</option>
                  </select>
                </div>
                <div>
                  <label for="ABV">ABV/SG</label>
                  <input type="text" class="form-control" id="ABV" placeholder="ABC/SG">
                </div>
                <div>
                  <label for="Directions">Directions</label>
                  <textarea class="form-control" id="Directions" rows="10" placeholder="Directions"></textarea>
                </div>
                <div>
                  <label for="Author">Author</label>
                  <input type="text" class="form-control" id="Author" placeholder="Author">
                </div>
                <div>
                  <br>
                  <button id="btnSaveReview" type="submit" class="btn btn-primary btn-sm">Save Review</button>
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
export default {

}
</script>

<style>

</style>