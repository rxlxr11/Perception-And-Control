<template>
  <div>
    <el-container  style="height: 500px; border: 1px solid #eee">
      <el-header style="font-size: 40px;
      background-color: #409eff;color: #eeeeee;
      text-align: center;
      font-family: 'Microsoft YaHei UI'">
        感知与控制 - 感知
      </el-header>
      <el-container>
        <el-aside width="200px" >
          <el-menu :default-openeds="['1', '3']">
            <el-submenu index="1">
              <template slot="title"><i class="el-icon-view"></i>感知</template>
              <el-menu-item index="1-1">
                <router-link to="/perception">感知</router-link>
              </el-menu-item>

              <el-menu-item index="1-2">
                <router-link to="/perception">感知</router-link>
              </el-menu-item>
            </el-submenu>
            <el-submenu index="2">

              <template slot="title"><i class="el-icon-upload2"></i>控制</template>
              <el-menu-item index="2-1">
                <router-link to="/control">控制</router-link>
              </el-menu-item>

              <el-menu-item index="2-2">
                <router-link to="/control">控制</router-link>
              </el-menu-item>
            </el-submenu>
          </el-menu>
        </el-aside>
        <el-main>
          <el-row>
            <el-col :span="8">
              <Card3></Card3>
            </el-col>
            <el-col :span="8">
              <Card2></Card2>
            </el-col>
            <el-col :span="8">
              <Card1 :mode=true>
              </Card1>
            </el-col>
          </el-row>
          <br><br>
          <drawer :mode="true"></drawer>
          <br><br>
          <div class="main-con">
            <echarts
                :width="width"
                :height="height"
                :id="id">
            </echarts>
          </div>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import echarts from '@/views/PerAndCon/component/Echarts.vue'
import Drawer from "@/views/PerAndCon/component/Drawer.vue";
import Card3 from "@/views/PerAndCon/component/Card3.vue";
import Card2 from "@/views/PerAndCon/component/Card2.vue";
import Card1 from "@/views/PerAndCon/component/Card1.vue";

export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: 'perception',
  data(){
    return{
      width: '100%',
      height: '500px',
      id: 'chartShow',
      value2: "false",
      value1: "true"
    }
  },
  methods: {

  },
  mounted() {
    //发送异步请求
  },

  components:{
    Card3,
    Card2,
    Card1,
    Drawer,
    echarts
  }

}

</script>

<style lang="css" scoped>
.main-con{
  width: 100%;
  height: 100%;
  padding: 20px;
}



</style>