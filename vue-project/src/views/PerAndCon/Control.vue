<template>
  <div>
    <el-container  style="height: 500px; border: 1px solid #eee">
      <el-header style="font-size: 40px;
      background-color: #409eff;color: #eeeeee;
      text-align: center;
      font-family: 'Microsoft YaHei UI'">
        感知与控制 - 控制
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
              <Card2></Card2>
            </el-col>
            <el-col :span="8">
              <Card1 :disabled=false></Card1>
            </el-col>
            <el-col :span="8">
              <Card4></Card4>
            </el-col>
          </el-row>
          <br>
          <el-row>
            <el-col :span="5">
              <el-input v-model="input" placeholder="请输入内容" size="medium"></el-input>
            </el-col>
            <el-col :span="3">
              <el-button type="primary" @click="submitTemp">确认</el-button>
            </el-col>
            <el-col :span="5">
              <el-radio v-model="radio" value="1" label="1" size="medium" >打开</el-radio>
              <el-radio v-model="radio" value="2" label="2" size="medium" >关闭</el-radio>
            </el-col>
            <el-col :span="4">
              <el-button type="primary" @click="submitCompressor">确认</el-button>
            </el-col>
          </el-row>
          <br><br><br><br>
          <drawer :mode=false ref="switch">

          </drawer>
          <div class="drawerS">
            <el-button  @click="submitDrawer" type="primary" size="big"  class="sbutton" style="font-size: 25px ;font-weight: bold">确认</el-button>
          </div>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import Drawer from "@/views/PerAndCon/component/Drawer.vue";
import Card2 from "@/views/PerAndCon/component/Card2.vue";
import Card1 from "@/views/PerAndCon/component/Card1.vue";
import Card4 from "@/views/PerAndCon/component/Card4.vue";
import axios from "axios";
axios.defaults.headers.common['Content-Type'] = 'application/json';


export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: 'control',
  data(){
    return{
      input:" ",
      radio:'1'
    }
  },
  methods: {
    submitDrawer ()
    {
      const data ={

          drawers: [this.$refs.switch.value[0],
                  this.$refs.switch.value[1],
                  this.$refs.switch.value[2],
                  this.$refs.switch.value[3],
                  this.$refs.switch.value[4],
                  this.$refs.switch.value[5],
                  this.$refs.switch.value[6],
                  this.$refs.switch.value[7],
                  this.$refs.switch.value[8],
                  this.$refs.switch.value[9] ]
      };

      // 发送 PUT 请求给后端
      //http://localhost:8080/setCompressor http://yapi.smart-xwork.cn/mock/264565/SetDrawer
      axios.post('http://localhost:8080/setDrawer', data)
          .then(response => {
            // 请求成功处理
            console.log(response.data.message);
            alert(response.data.message);
          })
          .catch(error => {
            // 请求失败处理
            console.error(error);
            alert("error");
          });
      alert("请求已发送");
    },
    submitCompressor ()
    {
      const data ={
        compressor: this.radio

      };

      //http://localhost:8080/perception http://yapi.smart-xwork.cn/mock/264565/SetCompressor
      // 发送 PUT 请求给后端
      axios.post('http://localhost:8080/setCompressor', data)
          .then(response => {
            // 请求成功处理
            console.log(response.data.message);
            alert(response.data.message);
          })
          .catch(error => {
            // 请求失败处理
            console.error(error);
            alert("response.data.message");
          });
      alert("请求已发送");
    },
    submitTemp ()
    {
      const data ={
        temperature: this.input
      };

      // 发送 PUT 请求给后端
      //http://localhost:8080/perception
      //http://yapi.smart-xwork.cn/mock/264565/SetCompressor
      axios.post('http://localhost:8080/setTemp', data)
          .then(response => {
            // 请求成功处理
            console.log(response.data.message);

          })
          .catch(error => {
            // 请求失败处理
            console.error(error);
          });
      alert("请求已发送");
    }

  },
  mounted() {
    //发送异步请求
  },

  components:{
    Card1,
    Card2,
    Card4,
    Drawer
  }

}

</script>

<style lang="css" scoped>


.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both
}

.box-card {
  width: 350px;
  height: 200px;
}
.drawerS{
  display: flex;
  justify-content: center;
  align-items: center;
}
.sbutton{
  height: 50px;
  width: 200px;
}

</style>