<template>
  <div class="switch" v-loading="loading" :style="{mode: mode}">
    <div class="switch1">
    <el-row :gutter="72">
      <el-col :span="4" >
        <el-switch
            style="display: block"
            v-model="value[0]"
            active-color="#409eff"
            inactive-color="#ff4949"
            inactive-text="抽屉1"
            :disabled=mode>
        </el-switch>
      </el-col>
      <el-col :span="4" >
        <el-switch
            style="display: block"
            v-model="value[1]"
            active-color="#409eff"
            inactive-color="#ff4949"
            inactive-text="抽屉2"
            :disabled=mode>
        </el-switch>
      </el-col>
      <el-col :span="4" >
        <el-switch
            style="display: block"
            v-model="value[2]"
            active-color="#409eff"
            inactive-color="#ff4949"
            inactive-text="抽屉3"
            :disabled=mode>
        </el-switch>
      </el-col>
      <el-col :span="4" >
        <el-switch
            style="display: block"
            v-model="value[3]"
            active-color="#409eff"
            inactive-color="#ff4949"
            inactive-text="抽屉4"
            :disabled=mode>
        </el-switch>
      </el-col>
      <el-col :span="4" >
        <el-switch
            style="display: block"
            v-model="value[4]"
            active-color="#409eff"
            inactive-color="#ff4949"
            inactive-text="抽屉5"
            :disabled=mode>
        </el-switch>
      </el-col>
    </el-row>
    </div>
    <br><br><br><br>
    <div class="switch2">
    <el-row :gutter="72">
      <el-col :span="4">
        <el-switch
            style="display: block"
            v-model="value[5]"
            active-color="#409eff"
            inactive-color="#ff4949"
            inactive-text="抽屉6"
            :disabled= mode>
        </el-switch>
      </el-col>
      <el-col :span="4">
        <el-switch
            style="display: block"
            v-model="value[6]"
            active-color="#409eff"
            inactive-color="#ff4949"
            inactive-text="抽屉7"
            :disabled= mode>
        </el-switch>
      </el-col>
      <el-col :span="4">
        <el-switch
            style="display: block"
            v-model="value[7]"
            active-color="#409eff"
            inactive-color="#ff4949"
            inactive-text="抽屉8"
            :disabled= mode>
        </el-switch>
      </el-col>
      <el-col :span="4">
        <el-switch
            style="display: block"
            v-model="value[8]"
            active-color="#409eff"
            inactive-color="#ff4949"
            inactive-text="抽屉9"
            :disabled = mode>
        </el-switch>
      </el-col>
      <el-col :span="4">
        <el-switch
            style="display: block "
            v-model="value[9]"
            active-color="#409eff"
            inactive-color="#ff4949"
            inactive-text="抽屉10"
            :disabled = mode>
        </el-switch>
      </el-col>
    </el-row>
    </div>
    <br><br>
    <br><br>
  </div>
</template>

<script>
import axios from "axios";

export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: "Drawer",
  props: {
    mode: {
      type: Boolean,
      default: false,
    }
  },
  data() {
    return {
      value: [],
      loading: true
    }
  },
  methods: {

  },
  mounted() {
    axios.get("http://localhost:8080/perception").then((result) => {
    //axios.get("http://yapi.smart-xwork.cn/mock/264565/drawer").then((result) => {
      this.value= result.data.value.drawer;
      //this.value= result.data.result.value;
      this.loading = false;
    });
  }
}
</script>

<style scoped>
.switch1{
  display: flex;
  justify-content: center;
  zoom: 1.5;
}
.switch2{
  display: flex;
  justify-content: center;
  zoom: 1.5;
}
body{
  margin: 0;
}
</style>