<template>
<div>
  <el-row>
    <el-col :span="8" >
      <el-card class="box-card">
        <div slot="header" class="clearfix">
          <span style="font-weight: bold;font-size: 25px">实时温度</span>

        </div>
        <div>
          <i class="el-icon-sunny"></i>
        </div>
      </el-card>
    </el-col>
    <el-col :span="8" >
      <el-card class="box-card">
        <div slot="header" class="clearfix">
          <span style="font-weight: bold;font-size: 25px">设定温度</span>

        </div>
        <div>
          <i class="el-icon-setting"></i>
        </div>
      </el-card>
    </el-col>
    <el-col :span="8" >
      <el-card class="box-card">
        <div slot="header" class="clearfix">
          <span style="font-weight: bold;font-size: 25px">压缩机</span>

        </div>
        <div>
          <i class="el-icon-cpu" ></i>
          <el-switch
              style="float: right "
              active-color="#13ce66"
              inactive-color = "#ff4949"
              inactive-text="状态"
              class="switch-compressor"
          >
          </el-switch>
        </div>
      </el-card>
    </el-col>
  </el-row>
</div>
</template>

<script>
export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: "Card",
  data(){

  },
  mounted() {
  },
  methods(){

  }
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
.time {
  font-size: 13px;
  color: #999;
}

.bottom {
  margin-top: 13px;
  line-height: 12px;
}

.button {
  padding: 0;
  float: right;
}

.image {
  width: 100%;
  display: block;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both
}
.el-icon-cpu{
  font-size: 100px;
  color: #407eff;
}
.el-icon-setting{
  font-size: 100px;
  color: #407eff;
}
.el-icon-sunny{
  font-size: 100px;
  color: #ffff1e;
}
.switch-compressor{
  zoom: 1.5;
}
</style>