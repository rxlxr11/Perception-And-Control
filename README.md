# Perception-And-Control
# 题目

现场快递柜状态采集与控制系统

## 目标 

设计实现一个对现场快递柜状态数据采集、显示、参数设置、抽屉打开、保鲜控制等功能软件系统。  

![](https://github.com/rxlxr11/Perception-And-Control.git\picture\test.PNG)

## 主要内容

1. 设计实现快递柜控制板仿真软件， 如下图所示， 实现对快递柜控制板状态数据的采集与显示，包括当前温度、 设定温度（控制温度）、 压缩机状态、 10 个抽屉的开关状态。其中当前温度可以设置某种变化规律， 例如压缩机启动后， 每秒钟当前温度降 0.5 度， 但不能超过最低下限值， 例如-40 度， 压缩机关闭后， 当前温度每秒上升 0.2 度， 但不能超过最高上限值， 例如 60 度等。

2. 在快递柜控制板仿真软件中， 依据控制温度和压缩机的启停控制，实现对快递柜控制板温度的控制，控制精度为 1 度。

3. 理解快递柜控制板仿真软件的通信协议（见附件），并设计实现，进而实现与快递柜控制板仿真软件（见附件） 的通信（对于控制命令要考虑通过握手机制实现可靠传输）。

4. 设计实现现场快递柜状态采集与控制系统软件， 如图 1 所示。 实现对所控制快递柜控制板状态数据的采集与显示，包括当前温度、 设定温度（控制温度）、 压缩机工作状态、10 个抽屉的开关状态等； 同时， 实现对开关指定抽屉、启停温度控制（压缩机制冷控制）、设置控制温度、以及设置系统参数等设置操作。

5. 在现场快递柜状态采集与控制系统软件中， 可以以曲线方式显示 1 小时内的当前温度和设定温度的变化趋势， 控制板的温度采集间隔为 10 秒  。

# 结果

![](F:\help\PAC\PAC_Project\picture\result\1.PNG)

![](F:\help\PAC\PAC_Project\picture\result\6.PNG)

![](F:\help\PAC\PAC_Project\picture\result\2.PNG)

![](F:\help\PAC\PAC_Project\picture\result\3.PNG)

![](F:\help\PAC\PAC_Project\picture\result\4.PNG)

![](F:\help\PAC\PAC_Project\picture\result\5.PNG)

# 前端Vue

用axios异步获取数据和传输数据，传输数据时要将axios的数据设置为json格式axios.defaults.headers.common['Content-Type'] = 'application/json';  

## 组件

每个组件要将其导出

### 卡片

```JavaScript
<template>  
<div :style="{mode:mode}">  
      <el-card class="box-card" v-loading="loading">  
        <div slot="header" class="clearfix">  
          <span style="font-weight: bold;font-size: 25px">压缩机</span>  
        </div>        
        <div>          
        <i class="el-icon-cpu"></i>  
          <span style="font-size: 55px;float: right" >{{value}}</span>  
        </div>      
        </el-card>
        </div>  
</template>  
  
<script>  
import axios from "axios";  
  
  
export default {  
  // eslint-disable-next-line vue/multi-word-component-names  
  name: "Card1",  
  props: {  
    mode: {  
      type: Boolean,  
      default: false  
    }  
  },  
  data(){  
    return {  
      value: "停止" ,  
      loading: true  
    }  
  },  
  mounted() {  
    axios.get("http://localhost:8080/perception").then((result) => {   
      this.value= result.data.value.compressor;  
      this.loading = false;  
    });  
  },  
  methods: {  
  
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
  
</style>
```

### 图表

定义一个drawline方法，画图

```JavaScript
<template>  
  <div class="Echarts">  
    <div :style="{ height: height, width: width }" :id="id" v-loading="loading"></div>  
  </div></template>  
  
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

    axios.get("http://localhost:8080/perception").then((result) => {  
        this.chartData.categories = result.data.categories;  
        this.chartData.temp = result.data.temp;  
        this.chartData.setting_temp = result.data.setting_temp;  
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
```

### 开关

开关在感知页面禁用，只有在控制页面可以使用，定义一个mode属性 ，当其为true时，disabled启用

```JavaScript
<template>  
  <div class="switch" v-loading="loading" :style="{mode: mode}">  
    <div class="switch1">  
    <el-row :gutter="72">  
      <el-col :span="4" >  
        <el-switch            style="display: block"  
            v-model="value[0]"  
            active-color="#409eff"  
            inactive-color="#ff4949"  
            inactive-text="抽屉1"  
            :disabled=mode>  
        </el-switch>      </el-col>      <el-col :span="4" >  
        <el-switch            style="display: block"  
            v-model="value[1]"  
            active-color="#409eff"  
            inactive-color="#ff4949"  
            inactive-text="抽屉2"  
            :disabled=mode>  
        </el-switch>      </el-col>      <el-col :span="4" >  
        <el-switch            style="display: block"  
            v-model="value[2]"  
            active-color="#409eff"  
            inactive-color="#ff4949"  
            inactive-text="抽屉3"  
            :disabled=mode>  
        </el-switch>      </el-col>      <el-col :span="4" >  
        <el-switch            style="display: block"  
            v-model="value[3]"  
            active-color="#409eff"  
            inactive-color="#ff4949"  
            inactive-text="抽屉4"  
            :disabled=mode>  
        </el-switch>      </el-col>      <el-col :span="4" >  
        <el-switch            style="display: block"  
            v-model="value[4]"  
            active-color="#409eff"  
            inactive-color="#ff4949"  
            inactive-text="抽屉5"  
            :disabled=mode>  
        </el-switch>      </el-col>    </el-row>    </div>    <br><br><br><br>    <div class="switch2">  
    <el-row :gutter="72">  
      <el-col :span="4">  
        <el-switch            style="display: block"  
            v-model="value[5]"  
            active-color="#409eff"  
            inactive-color="#ff4949"  
            inactive-text="抽屉6"  
            :disabled= mode>  
        </el-switch>      </el-col>      <el-col :span="4">  
        <el-switch            style="display: block"  
            v-model="value[6]"  
            active-color="#409eff"  
            inactive-color="#ff4949"  
            inactive-text="抽屉7"  
            :disabled= mode>  
        </el-switch>      </el-col>      <el-col :span="4">  
        <el-switch            style="display: block"  
            v-model="value[7]"  
            active-color="#409eff"  
            inactive-color="#ff4949"  
            inactive-text="抽屉8"  
            :disabled= mode>  
        </el-switch>      </el-col>      <el-col :span="4">  
        <el-switch            style="display: block"  
            v-model="value[8]"  
            active-color="#409eff"  
            inactive-color="#ff4949"  
            inactive-text="抽屉9"  
            :disabled = mode>  
        </el-switch>      </el-col>      <el-col :span="4">  
        <el-switch            style="display: block "  
            v-model="value[9]"  
            active-color="#409eff"  
            inactive-color="#ff4949"  
            inactive-text="抽屉10"  
            :disabled = mode>  
        </el-switch>      </el-col>    </el-row>    </div>    <br><br>    <br><br>  </div></template>  
  
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
```

## 页面

分为头部，侧边和主体并导入有关模块，要使用模块中的数据要使用refs

### 感知

```JavaScript
<template>  
  <div>    <el-container  style="height: 500px; border: 1px solid #eee">  
      <el-header style="font-size: 40px;  
      background-color: #409eff;color: #eeeeee;  
      text-align: center;  
      font-family: 'Microsoft YaHei UI'">  
        感知与控制 - 感知  
      </el-header>  
      <el-container>        <el-aside width="200px" >  
          <el-menu :default-openeds="['1', '3']">  
            <el-submenu index="1">  
              <template slot="title"><i class="el-icon-view"></i>感知</template>  
              <el-menu-item index="1-1">  
                <router-link to="/perception">感知</router-link>  
              </el-menu-item>  
              <el-menu-item index="1-2">  
                <router-link to="/perception">感知</router-link>  
              </el-menu-item>            </el-submenu>            <el-submenu index="2">  
  
              <template slot="title"><i class="el-icon-upload2"></i>控制</template>  
              <el-menu-item index="2-1">  
                <router-link to="/control">控制</router-link>  
              </el-menu-item>  
              <el-menu-item index="2-2">  
                <router-link to="/control">控制</router-link>  
              </el-menu-item>            </el-submenu>          </el-menu>        </el-aside>        <el-main>          <el-row>            <el-col :span="8">  
              <Card3></Card3>            </el-col>            <el-col :span="8">  
              <Card2></Card2>            </el-col>            <el-col :span="8">  
              <Card1 :mode=true>  
              </Card1>            </el-col>          </el-row>          <br><br>          <drawer :mode="true"></drawer>  
          <br><br>          <div class="main-con">  
            <echarts                :width="width"  
                :height="height"  
                :id="id">  
            </echarts>          </div>        </el-main>      </el-container>    </el-container>  </div></template>  
  
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
```

### 控制

定义多个点击按钮事件触发的方法，触发方法后会异步传输数据

```JavaScript
<template>  
  <div>    <el-container  style="height: 500px; border: 1px solid #eee">  
      <el-header style="font-size: 40px;  
      background-color: #409eff;color: #eeeeee;  
      text-align: center;  
      font-family: 'Microsoft YaHei UI'">  
        感知与控制 - 控制  
      </el-header>  
      <el-container>        <el-aside width="200px" >  
          <el-menu :default-openeds="['1', '3']">  
            <el-submenu index="1">  
              <template slot="title"><i class="el-icon-view"></i>感知</template>  
              <el-menu-item index="1-1">  
                <router-link to="/perception">感知</router-link>  
              </el-menu-item>  
              <el-menu-item index="1-2">  
                <router-link to="/perception">感知</router-link>  
              </el-menu-item>            </el-submenu>            <el-submenu index="2">  
  
              <template slot="title"><i class="el-icon-upload2"></i>控制</template>  
              <el-menu-item index="2-1">  
                <router-link to="/control">控制</router-link>  
              </el-menu-item>  
              <el-menu-item index="2-2">  
                <router-link to="/control">控制</router-link>  
              </el-menu-item>            </el-submenu>          </el-menu>        </el-aside>        <el-main>          <el-row>            <el-col :span="8">  
              <Card2></Card2>            </el-col>            <el-col :span="8">  
              <Card1 :disabled=false></Card1>  
            </el-col>            <el-col :span="8">  
              <Card4></Card4>            </el-col>          </el-row>          <br>          <el-row>            <el-col :span="5">  
              <el-input v-model="input" placeholder="请输入内容" size="medium"></el-input>  
            </el-col>            <el-col :span="3">  
              <el-button type="primary" @click="submitTemp">确认</el-button>  
            </el-col>            <el-col :span="5">  
              <el-radio v-model="radio" value="1" label="1" size="medium" >打开</el-radio>  
              <el-radio v-model="radio" value="2" label="2" size="medium" >关闭</el-radio>  
            </el-col>            <el-col :span="4">  
              <el-button type="primary" @click="submitCompressor">确认</el-button>  
            </el-col>          </el-row>          <br><br><br><br>          <drawer :mode=false ref="switch">  
  
          </drawer>          <div class="drawerS">  
            <el-button  @click="submitDrawer" type="primary" size="big"  class="sbutton" style="font-size: 25px ;font-weight: bold">确认</el-button>  
          </div>        </el-main>      </el-container>    </el-container>  </div></template>  
  
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
      //http://yapi.smart-xwork.cn/mock/264565/SetCompressor      axios.post('http://localhost:8080/setTemp', data)  
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
```

# 后端SpringBoot

## 串口

### 读

设置一个静态变量表示读到的帧，方便之后操作，判断帧最后2个字节是否为FFF7，若是则该帧读完，重新开始读一个新的帧

```Java
public class SerialReader implements Runnable  
{  
    private InputStream in;  
    public static String read_Frame;  
    public SerialReader(InputStream in )  
    {  
        this.in = in;  
    }  
  
    public void run ()  
    {  
   
        byte[] buffer = new byte[44];  
        int len = buffer.length;  
        int readBytes=0;  
  
        try        {  
            while ( readBytes < len )  
            {  
  
                int read = in.read(buffer, readBytes, len - readBytes);  
                readBytes += read;  
                String s = Tool.bytesToHexString(buffer,readBytes);  
                StringBuilder sb=new StringBuilder(s);  
                System.out.println(read_Frame);  
                if (s.length() >= 4) {  
                    if (readBytes == 44 || sb.substring(s.length() - 4, s.length()).toString().equals("FFF7")) {  
                        if (readBytes == 44){  
                            read_Frame = Tool.bytesToHexString(buffer, len);}  
                        readBytes = 0;  
                    }  
                }  
  
            }  
            readBytes = 0;   
        }  
        catch ( Exception e )  
        {  
            e.printStackTrace();  
        }  
    }  
}
```

### 写

设置一个静态变量flag，要写数据的时候将flag置为true即可

```Java
public class SerialWriter implements Runnable {  
    OutputStream out;  
    public static boolean flag = false;  
  
    public SerialWriter(OutputStream out) {  
        this.out = out;  
    }  
  
    public static String frame ;  

    public void run() {  
        try {  
            while (true)  
            {  
                if (flag){  
                    byte[] data = Tool.hexStringToBytes(frame);  
                    this.out.write(data);  
                    flag = false;  
                }  
  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
}
```

### 启动

设置串口号，波特率等属性，启动读写线程

```Java
 // 在项目启动后执行的操作
        CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier("COM1");
        CommPort commPort = portIdentifier.open(this.getClass().getName(),2000);
        if ( commPort instanceof SerialPort)
        {
            SerialPort serialPort = (SerialPort) commPort;
            serialPort.setSerialPortParams(38400,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);

            InputStream in = serialPort.getInputStream();
            OutputStream out = serialPort.getOutputStream();
            SerialReader sr = new SerialReader(in);
            Thread read = new Thread(sr);
            SerialWriter sw = new SerialWriter(out);
            Thread writer = new Thread(sw);
            System.out.println("start");
            read.start();
            writer.start();
```

## 帧

### 帧格式

![](F:\课程\感知与控制\picture\frameStruct.PNG)

```Java
public class FrameStruct {  
    public static String header = "ffff";  
    public static String end = "fff7";  
    public String Frame_Len;  
    public String Frame_Num;  
    public String Device_Addr;  
    public String Func_Num;  
    public String Data;  
    public String Check = "0000";  
    public String CRC_Func(){  
        return "ffff";  
    }  
  
    public FrameStruct(){  
  
    }  
  
    public  String encapsulate(){  
  
        StringBuilder sb = new StringBuilder();  
        sb.append(this.header).append(this.Frame_Len).append(Frame_Num).append(this.Device_Addr);  
        sb.append(this.Func_Num).append(this.Data).append(this.Check).append(this.end);  
  
        return sb.toString();  
    }  
  
}
```

### 上传状态帧

![](F:\课程\感知与控制\picture\statusFrame.PNG)

```Java
public class StatusFrame extends FrameStruct{  
    public data getFrame_Data(){  
        System.out.println(this.Data);  
        data d = new data();  
        //StringBuilder sb = new StringBuilder(this.Data);  
        d.setCompressor(handleCompressor());  
        d.setDrawer(handleDrawer());  
        d.setTemp(handleTemp(54,56));  
        d.setSetting_temp(handleTemp(52,54));  
        if (data.temp_array.length < 20){  
            data.temp_array[data.temp_array.length-1]=d.getTemp();  
        }else {  
            for (int i = 0 ; i<19 ; i ++){  
                data.temp_array[i] = data.temp_array[i+1];  
            }  
            data.temp_array[19] = d.getTemp();  
        }  
        if (data.setting_array.length<20){  
            data.setting_array[data.setting_array.length-1]=d.getSetting_temp();  
        }else {  
            for (int i = 0 ; i<19 ; i ++){  
                data.setting_array[i] = data.setting_array[i+1];  
            }  
            data.setting_array[19] = d.getSetting_temp();  
        }  
        return d;  
    }  
    public sysPara handleSys(){  
        StringBuilder sb = new StringBuilder(this.Data);  
        sysPara sp = new sysPara();  
        String s = sb.substring(22,24);  
        s = Tool.hexTOdec(s);  
        sp.setDev_Addr(s);  
        s = sb.substring(28,30);  
        s = Tool.hexTOdec(s);  
        sp.setDev_Addr(s);  
        s = sb.substring(36,38);  
        s = Tool.hexTOdec(s);  
        sp.setDev_Addr(s);  
  
      return sp;  
  
    }  
    public String handleCompressor(){  
        String s = this.Data;  
        StringBuilder sb = new StringBuilder(s);  
        s = sb.substring(50,52);  
        switch (s) {  
            case "00":  
                return "停止";  
            case "01":  
                return "预启动";  
            case "02":  
                return "启动";  
            default:  
                return "故障";  
        }  
    }  
  
  
    public float handleTemp(int a,int b){  
        String s = this.Data;  
        StringBuilder sb2 = new StringBuilder(s);  
        s = sb2.substring(a,b);  
        float temp = 0f;     
        log.warn("还不可以认输"+s);  
        s = Tool.hexString2binaryString(s,8);  
        log.warn("op怎么你了"+s);  
        StringBuilder sb = new StringBuilder(s);  
        String item;  
        item = sb.substring(0,1);  
        if (item.equals("1")){  
            temp+= 0.5f;  
        }  
        for (int i = 1; i < 7; i++) {  
            item = sb.substring(i,i+1);  
            if (item.equals("1")){  
                temp+=1*pow(2,6-i);  
            }  
  
        }  
        item = sb.substring(7,8);  
        if (item.equals("1")){  
            temp=-temp;  
        }  
        return temp;  
    }  
  
    public boolean[] handleDrawer(){  
        String s = this.Data;  
        StringBuilder sb2 = new StringBuilder(s);  
        s = sb2.substring(60,64);  
        s = Tool.hexString2binaryString(s,16);  
        StringBuilder sb = new StringBuilder(s);  
        boolean[] drawer = new boolean[10];  
        for (int i = 0; i < 7; i++) {  
            drawer[7-i] = sb.substring(i, i + 1).equals("1");  
        }  
        drawer[9] = sb.substring(15, 16).equals("1");  
        drawer[8] = sb.substring(14, 15).equals("1");  
        return drawer;  
    }  
  
  
    public void decapsulate(String frame){  
  
        StringBuilder sb = new StringBuilder(frame);  
        header = sb.substring(0,4);  
        Frame_Len = sb.substring(4,6);  
        Frame_Num = sb.substring(6,8);  
        Device_Addr = sb.substring(8,10);  
        Func_Num = sb.substring(10,12);  
        Data = sb.substring(12,frame.length()-8);  
        Check = sb.substring(frame.length()-8,frame.length()-4);  
        end = sb.substring(frame.length()-4,frame.length());  
  
  
    }  
  
    public static void main(String[] args) {  
        StatusFrame sf = new StatusFrame();  
        //sf = sf.decapsulate(f);  
        System.out.println(sf.Data);  
        data d = new data();  
        d= sf.getFrame_Data();  
        System.out.println(d.getCompressor());  
    }  
}
```

### 设置参数帧

![](F:\课程\感知与控制\picture\sysPara.PNG)

```Java
public class SetSysParaFrame extends FrameStruct{  
    public SetSysParaFrame (sysPara sys){  
        this.Frame_Len    = "1C";  
        this.Func_Num     = "05";  
        this.Check        = "0000";  
        this.Frame_Num    = "01";  
        this.Device_Addr  = "00";  
        this.Data         = sys(sys);  
  
    }  
  
    private String sys(sysPara s){  
        StringBuilder sb = new StringBuilder();  
        sb.append("FFFFFFFFFF");  
        sb.append(s.getDev_Addr());  
        sb.append("0001");  
        sb.append(s.getDelay());  
        sb.append("0000");  
        sb.append("04");  
        System.out.println("全体目光想这看其"+s.getTemp_deviation());  
        sb.append(s.getTemp_deviation());  
        sb.append("FFFFFFFF00");  
        return sb.toString();  
    }  
}
```

### 设置温度帧

```Java
public class SetTempFrame extends FrameStruct{  
    public SetTempFrame (float set_temp){  
        this.Frame_Len    =  "0B"  ;  
        this.Func_Num     =  "04"  ;  
        this.Check        =  "0000";  
        this.Frame_Num    =  "10"  ;  
        this.Device_Addr  =  "01"  ;  
        this.Data         =  setTemp(set_temp)  ;  
  
    }  
    private String setTemp(float f){  
        StringBuilder sb = new StringBuilder();  
        int   a  = (int) f;  
        float b  = f-(float) a;  
        if ( Math.abs(b) < 0.5){  
            sb.append("0");  
        }else {  
            sb.append("1");  
        }  
        String s = Tool.decTObin(Integer.toString(Math.abs(a)));  
        log.error("砍我一击灭之"+s);  
        if (s.length() < 6){  
            for (int i = 0; i < 6 - s.length(); i++) {  
                sb.append("0");  
            }  
        }  
        sb.append(s);  
        if (f < 0){  
            sb.append("1");  
        }else {  
            sb.append("0");  
        }  
        return Tool.bin2hex(sb.toString());  
    }  
}
```

### 设置抽屉帧

```Java
public class DrawerConFrame extends FrameStruct{  
    public DrawerConFrame (boolean[] drawer){  
        this.Frame_Len    = "0C";  
        this.Func_Num     = "03";  
        this.Check        = "0000";  
        this.Frame_Num    = "03";  
        this.Device_Addr  = "00";  
        this.Data         = fit(drawer);  
  
    }  
    private String fit(boolean[] b){  
        boolean[] booleans = enlarge(b);  
        StringBuilder sb = new StringBuilder();  
        for (int i = 0 ; i< 16; i++){  
            if (booleans[i]){  
                sb.append("1");  
            }else{  
                sb.append("0");  
            }  
        }  
        System.out.println(sb.toString());  
        String S = Tool.bin2hex(sb.toString());  
        return S;  
    }  
  
    private boolean[] enlarge(boolean[] booleans){  
        boolean[] b = new  boolean[16];  
        for (int i = 0; i < 8; i++){  
            b[7-i] = booleans[i];  
        }  
        b[15] = booleans[9];  
        b[14] = booleans[8];  
        return b;  
    }  
}
```

### 设置压缩器帧

```Java
public class CompressorConFrame extends FrameStruct{  
    public CompressorConFrame(boolean b){  
        this.Frame_Len   = "0B";  
        this.Func_Num    = "02";  
        //this.Check     = CRC_Func();  
        this.Check       = "0000";  
        this.Frame_Num   = "04";  
        this.Device_Addr = "00";  
        if (b){  
            this.Data    = "01";  
        }else {  
            this.Data    = "00";  
        }  
    }  
  
}
```

## Controller

### PerController

前端对/perception，/GetSysPara的请求由PerController接收

```Java
public class PerController {  
    @Autowired  
    private PerService perService;    
    @CrossOrigin  
    @RequestMapping("/perception")  
    public result all(){  
        log.info("获取全部数据");  
        result r = new result();  
        r.setting_temp = data.setting_array;  
        r.temp = data.temp_array;   
        r.message = "success";  
        return r;  
    }  
    @CrossOrigin  
    @RequestMapping("/GetSysPara")  
    public result sysPara(){  
        log.info("compressor");  
        result r = new result();  
        r.value = perService.sysHandle();  
        r.message = "success";  
        r.code = 1;  
        return r;  
    }  
}
```

### ConController

前端发送的post请求由ConController处理

```Java
public class ConController {  
    @Autowired  
    private ConService conService;  
  
    @CrossOrigin  
    @RequestMapping ("/setCompressor")  
    public result setCompressor(@RequestBody compressor compre){  
  
        if (compre.getCompressor().equals("1"))  
            conService.sendCompressor(true);  
        else if (compre.getCompressor().equals("2")) {  
            conService.sendCompressor(false);  
        }  
        result r2 =  new result();  
        r2.code   =  1;  
        r2.message  =  "success";  
        return r2;  
    }  
    @CrossOrigin  
    @RequestMapping("/setDrawer")  
    public result SetDrawer(@RequestBody drawer d){  
        log.error("这里有错吗" +  
                "收到请回答"+d);  
        boolean[] drawer = d.getDrawers();  
        conService.sendDrawer(drawer);  
        log.error("lalaldemaxi" +  
                "收到请回答");  
        result r =  new result();  
        r.code   =  1;  
        r.message  =  "success";  
        return r;  
    }  
  
    @CrossOrigin  
    @RequestMapping("/setTemp")  
    public result setTemp(@RequestBody temp t){  
        log.error("发生甚魔事了"+t);  
        conService.sendTemp(t.getTemperature());  
        result r =  new result();  
        r.code   =  1;  
        r.message  =  "success";  
        return r;  
    }  
    @CrossOrigin  
    @RequestMapping ("/setSysPara")  
    public result SetPara(@RequestBody sysPara sp){  
        log.error("在你眼中我是谁"+sp);  
        conService.sendPara(sp);  
        result r =  new result();  
        r.code   =  1;  
        r.message  =  "success";  
        return r;  
    }  
}
```

## Service

对数据进行处理，准备好返回给前端的数据或者向串口发送的数据

### PerService

```Java
public class PerService {  
    @Autowired  
    private PerDao perDao ;  
    public result test(){  
        result r = perDao.test();  
        r.code = 2;  
        return r;  
  
    }  
  
    public sysPara sysHandle(){  
        StatusFrame statusFrame = perDao.getStatusFrame();  
        sysPara sp = statusFrame.handleSys();  
        return sp;  
    }  
    public data dataHandle(){  
        String frame = perDao.getFrame();  
        StatusFrame statusFrame = new StatusFrame();  
        statusFrame.decapsulate(frame);  
        return statusFrame.getFrame_Data();  
    }  
  
    public String compressorHandle(){  
        StatusFrame statusFrame = perDao.getStatusFrame();  
        //String s = statusFrame.handleCompressor();  
        return statusFrame.handleCompressor();  
    }  
    public Float tempHandle(){  
        StatusFrame statusFrame = perDao.getStatusFrame();  
        //Float a = statusFrame.handleTemp();  
        return statusFrame.handleTemp(54,56);  
    }  
  
    public Float SettingTemp_Handle(){  
        StatusFrame statusFrame = perDao.getStatusFrame();  
        //Float a = statusFrame.handle_SettingTemp();  
        return statusFrame.handleTemp(56,58);  
    }  
  
    public boolean[] drawerHandle(){  
        StatusFrame statusFrame = perDao.getStatusFrame();  
        return statusFrame.handleDrawer();  
    }  
    @Scheduled(fixedRate = 2500) // 每2.5秒执行一次  
    public data scheduleUpdate() {  
        log.info("update");  
        //System.out.println(data.temp_array[0]+" "+data.temp_array[1]);  
        data d = dataHandle();  
        return d;  
    }  
}
```

### ConService

```Java
public class ConService {  
    public void sendCompressor(boolean b){  
        log.info("绷不住了");  
        log.error("i dont know");  
        CompressorConFrame compressorConFrame = new CompressorConFrame(b);  
        SerialWriter.frame = compressorConFrame.encapsulate();  
        SerialWriter.flag = true;  
    }  
    public void sendDrawer(boolean[] b){  
        DrawerConFrame drawerConFrame = new DrawerConFrame(b);  
        SerialWriter.frame = drawerConFrame.encapsulate();  
        SerialWriter.flag = true;  
    }  
    public void sendPara(sysPara sp){  
        SetSysParaFrame sysParaFrame = new SetSysParaFrame(sp);  
        SerialWriter.frame = sysParaFrame.encapsulate();  
        SerialWriter.flag = true;  
    }  
    public void sendTemp(String s){  
        float a = Float.valueOf(s);  
        log.error("给我看看"+a);  
        SetTempFrame tempFrame = new SetTempFrame(a);  
        SerialWriter.frame = tempFrame.encapsulate();  
        SerialWriter.flag = true;  
    }  
}
```

## Dao

获取帧返回给Service

### PerDao

```Java
public class PerDao {  
    data d ;  
  
    public String getFrame(){  
        try  
        {  
            log.info("成功没说一声");  
            return SerialReader.read_Frame;  
        }  
        catch ( Exception e )  
        {  
            e.printStackTrace();  
        }  
  
        return "d";  
    }  
  
    public StatusFrame getStatusFrame(){  
        try  
        {  
  
            log.info("成功没说一声");  
  
            StatusFrame statusFrame = new StatusFrame();  
            statusFrame.decapsulate(SerialReader.read_Frame);  
            return statusFrame;  
        }  
        catch ( Exception e )  
        {  
            e.printStackTrace();  
        }  
  
        return new StatusFrame();  
    }  
  
}
```
