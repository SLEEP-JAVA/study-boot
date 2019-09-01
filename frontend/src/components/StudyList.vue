<template>
  <div style="padding: 20px" v-if="studies && studies.length">
    <el-card v-for="(study, index) in studies" :key="study.id" v-on:click.native="expand(index)">
      <div>
        <li>이름: {{ study.name }}</li>
        <li>시작일: {{ study.startDate }}</li>
        <li>종료일: {{ study.endDate }}</li>
        <li>장소: {{ study.place }}</li>
        <li>모집인원: 0 / {{ study.capacity }}</li>
      </div>
      <transition name="detail">
        <div v-if="expanded[index]">
          <span>설명: {{ study.description }}</span>
          <div v-if="study.properties && study.properties.length">
            <li v-for="property in study.properties">{{ property.name }}: {{ property.value }}</li>
          </div>
        </div>
      </transition>
    </el-card>
  </div>
</template>

<script>
  export default {
    name: 'studies',
    data: () => {
      return {
        studies: [],
        expanded: []
      }
    },
    created() {
      const baseURI = "/api/v1/studies";

      this.axios.get(`${baseURI}`)
        .then(response => {
          console.log(response);

          this.studies = response.data;
          this.expanded = Array(this.studies.length).fill(false);
        })
        .catch(error => {
          console.log(error);
        });
    },
    methods: {
      expand: function (index) {
        if (this.expanded[index]) {
          this.expanded = this.expanded.map(() => false);
          return;
        }

        this.expanded = this.expanded.map((x, i) => i === index);
      }
    }
  }
</script>
