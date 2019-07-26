<template>
  <div style="padding: 20px">
    <el-row type="flex" class="row-bg" justify="center">
      <el-col :span=12>
        <el-form ref="form" label-position="left" :model="form" label-width="120px">
          <el-form-item label="스터디명">
            <el-input v-model="form.name"></el-input>
          </el-form-item>
        <el-form-item label="카테고리">
              <el-select v-model="form.category" placeholder="카테고리를 선택해주세요.">
                 <el-option v-for="option in categoryOptions" :label=option :value=option></el-option>
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
            <el-input type="textarea" rows=20 v-model="form.desc"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button>좀더 생각해볼래요</el-button>
            <el-button type="primary" @click="onSubmit">✨스터디 만들기✨</el-button>
          </el-form-item>
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
          desc: ''
        },
        categoryOptions : [
          'FrontEnd 프론트엔드',
          'BackEnd 백엔드',
          'Algorithm 알고리즘',
          'Design 디자인'
        ],
        studyType : [
          '온라인 스터디',
          '오프라인 스터디',
          '토이 프로젝트',
          '책 함께 읽기',
          '발표 / 요약',
        ]
      }
    },
    methods: {
      onSubmit() {
        console.log('submit!');
      }
    }
  }
</script>
<style>


</style>
