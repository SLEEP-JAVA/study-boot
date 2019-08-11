<template>
  <div style="padding: 20px">

    <el-row type="flex" class="row-bg" justify="center">
      <el-col :span=12>
        <el-form ref="form" label-position="left" :model="form" label-width="120px" v-model=form>
          <el-form-item label="스터디명" :rules="[{ required: true, message: '스터디 명을 적어주세요.'}]">
            <el-input v-model="form.name"></el-input>
          </el-form-item>
          <el-form-item label="장소" :rules="[{ required: true, message: '장소를 채워주세요.!'}]">
            <el-input v-model="form.place"></el-input>
          </el-form-item>
          <el-form-item label="인원" :rules="[{ required: true, message: '인원을 선택해주세요.!'},{ type: 'number', message: '인원은 숫자여야합니다.'}]"
          >
            <el-select v-model="form.volume" placeholder="인원을 선택해주세요.">
              <el-option v-for="volume in volumeType" :label=volume :value=volume></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="카테고리" :rules="[{ required: true, message: '카테고리를 적어주세요!'}]">
            <el-select v-model="form.category" placeholder="카테고리를 선택해주세요.">
              <el-option v-for="option in categoryOptions" :label=option.title :value=option.value></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="스터디 기간">
            <el-col :span="11">
              <el-date-picker type="date" placeholder="시작일을 선택하세요 🙂" v-model="form.startDate" style="width: 100%;"></el-date-picker>
            </el-col>
            <el-col class="line" :span="2">-</el-col>
            <el-col :span="11">
              <el-date-picker placeholder="종료일을 선택하세요 🙂" v-model="form.endDate" style="width: 100%;"></el-date-picker>
            </el-col>
          </el-form-item>
          <el-form-item label="스터디 종류">
            <sub> 해당항목에 모두 체크해주세요! ✅ </sub>
            <el-checkbox-group v-model="form.type">
              <el-checkbox v-for="type in studyType" :label=type :name=type></el-checkbox>
            </el-checkbox-group>
          </el-form-item>
          <el-form-item label="스터디 설명">
            <el-input type="textarea" rows=20 v-model="form.description"></el-input>
          </el-form-item>
          <el-form-item
            v-for="props in form.properties"
            :label="'추가 항목'"
            :rules="{required: true, message: '추가 항목을 채워주세요.', trigger: 'blur'}"
          >
            <el-row>
              <el-col :span="7" > <el-input v-model="props.name" placeholder="항목의 이름을 적어주세요. e.g.) 스택"></el-input></el-col>
              <el-col :span="16" :offset="1"><el-input label="호이" v-model="props.value" placeholder="항목의 값을 적어주세요. e.g) Java 초급자"></el-input></el-col>
            </el-row>


          </el-form-item>
          <el-form-item>
            <el-button @click="addForm">항목 추가하기</el-button>
            <el-button type="primary" @click="onSubmit">✨스터디 만들기✨</el-button>
          </el-form-item>

          <el-alert
            v-show="alertShow"
            :title=alertTitle
            type="error"
            center
            show-icon>
          </el-alert>
        </el-form>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'StudyForm',
  data() {
    return {
      form: {
        name: '',
        category: '',
        startDate: '',
        endDate: '',
        type: [],
        description: '',
        place: '',
        volume: '',
        properties: []
      },
      categoryOptions: [
        {'title': 'FrontEnd 프론트엔드', 'value': 'FRONTEND'},
        {'title': 'BackEnd 백엔드', 'value': 'BACKEND'},
        {'title': 'Algorithm 알고리즘', 'value': 'ALGORITHM'},
        {'title': 'Design 디자인', 'value': 'DESIGN'}
      ],
      studyType: [
        '온라인 스터디',
        '오프라인 스터디',
        '토이 프로젝트',
        '책 함께 읽기',
        '발표 / 요약'
      ],
      volumeType: Array.from({length: 20}, (x, i) => i + 1),
      alertShow: false,
      alertTitle: 'Error Occurred'
    }
  },
  methods: {
    addForm () {
      this.form.properties.push({
        name: '',
        value: ''
      });
    },
    onSubmit () {
      this.createStudy()
    },
    createStudy () {
      const baseURI = "/api/v1/users/1/studies" //TODO userId 적용

      this.form.startDate = this.$moment(this.form.startDate).format('YYYY-MM-DD')
      this.form.endDate = this.$moment(this.form.endDate).format('YYYY-MM-DD')
      this.axios.post(`${baseURI}`, this.form).then((result) => {
        console.log(result)
      }).catch((error) => {
         this.alertShow = true
         this.alertTitle= error.response.data.message
         console.log(error)
      })
    }
  }
}
</script>

<style>
  .fade-enter-active, .fade-leave-active {
    transition: all .6s ease;
  }

  .fade-enter, .fade-leave-to /* .fade-leave-active below version 2.1.8 */
  {
    opacity: 0;
  }
</style>
