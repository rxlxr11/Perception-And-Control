//在Echarts.vue文件中
<template>
  <div class="Echarts">
    <div :style="{ height: height, width: width }" :id="id" v-loading="loading"></div>
  </div>
</template>

<script>
import axios from "axios";

let echarts = require("echarts/lib/echarts");
export default {

  // eslint-disable-next-line vue/multi-word-component-names
  name: "Echarts",
  props: {
    height: {
      type: String,
      default: "700px",
    },
    width: {
      type: String,
      default: "1200px",
    },
    id: {
      type: String,
      default: "demo",
    },
  },
  data() {
    return {
      chartData:
        {
          "categories": [],
          "temp" :  [],
          "setting_temp":[]
        },
        loading: true
    };
  },
  mounted() {
    //axios.get("http://yapi.smart-xwork.cn/mock/264565/views/PerAndCon/echarts").then((result) => {
    axios.get("http://localhost:8080/perception").then((result) => {
        this.chartData.categories = result.data.categories;
        //this.chartData.categories = result.data.result.categories;
        this.chartData.temp = result.data.temp;
        //this.chartData.temp = result.data.result.series[0].data;
        this.chartData.setting_temp = result.data.setting_temp;
        //this.chartData.setting_temp = result.data.result.series[1].data;
        this.drawLine(this.chartData);
        this.loading = false
      });

  },
  methods: {
    drawLine(data) {
      // 基于准备好的dom，初始化echarts实例
      console.log(this.id);
      let myChart = echarts.init(document.getElementById(this.id));
      // 绘制图表
      myChart.setOption({
        title: {
          text: '温度与设定温度随时间的变化',
        },
        tooltip: {},
        legend: {},
        xAxis: {
          data: data.categories
        },
        yAxis: {},
        series: [
          {
            name: '温度',
            type: 'line',
            stack: 'x',
            data: data.temp
          },
          {
            name: '设定温度',
            type: 'line',
            stack: 'x',
            data: data.setting_temp
          }
        ]
      });
    },
  },
};
</script>


<style>

</style>