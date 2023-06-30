<template>
  <div>

    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span style="font-weight: bold;font-size: 25px">系统参数</span>
        <el-button type="text" @click="dialogFormVisible = true" style="float: right; padding: 3px 0">修改</el-button>
      </div>
      <div  class="text item">
        <span style="font-size: 18px ;text-align: center;font-weight: bold;color: #409eff">设 备 地 址 :   </span>
        <span style="font-size: 18px ;float: right;font-weight: bold" >{{form.Dev_Addr}}</span><br><br>
        <span style="font-size: 18px;text-align: center;font-weight: bold;color: #409eff">温度控制偏差:</span>
        <span style="font-size: 18px ;float: right;font-weight: bold">{{form.Temp_deviation}}</span><br><br>
        <span style="font-size: 18px;text-align: center;font-weight: bold;color: #409eff">压缩机启动延时:</span>
        <span style="font-size: 18px ;float: right;font-weight: bold">{{form.Delay}}</span>
      </div>
    </el-card>

    <el-dialog title="系统参数" :visible.sync="dialogFormVisible">
      <el-form :model="form">
        <el-form-item label="设备地址" :label-width="formLabelWidth">
          <el-input v-model="form.Dev_Addr" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="温度控制偏差" :label-width="formLabelWidth">
          <el-input v-model="form.Temp_deviation" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="压缩机启动延时" :label-width="formLabelWidth">
          <el-input v-model="form.Delay" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitSysPara">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>

import axios from "axios";
axios.defaults.headers.common['Content-Type'] = 'application/json';

export default {

  // eslint-disable-next-line vue/multi-word-component-names
  name: "Card4",
  data() {

    return {
      dialogFormVisible: false,
      form: {
        Dev_Addr      :       "..",
        Temp_deviation:       "..",
        Delay         :       ".."
      },
      formLabelWidth: '120px',
      loading : true
    };
  },
  methods: {
    submitSysPara ()
    {

      const data ={
        Dev_Addr :       this.form.Dev_Addr,
        Temp_deviation:  this.form.Temp_deviation,
        Delay:           this.form.Delay
      };

      // 发送 PUT 请求给后端
      //http://localhost:8080/perception
      //http://yapi.smart-xwork.cn/mock/264565/SetSyaPara
      axios.post('http://localhost:8080/setSysPara', data)
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
    //http://yapi.smart-xwork.cn/mock/264565/GetSysPara
    //axios.get("http://localhost:8080/perception").then((result) => {
    axios.get("http://localhost:8080/GetSysPara").then((result) => {
      this.form.Dev_Addr       =  result.data.value.dev_Addr;
      this.form.Temp_deviation =  result.data.value.temp_deviation;
      this.form.Delay          =  result.data.value.delay;
      this.loading             =  false;
    });
  },
}
</script>

<style scoped>
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


.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both
}

.el-icon-sunny {
  font-size: 100px;
  color: #ffff1e;
}

body {
  margin: 0;
}
</style>