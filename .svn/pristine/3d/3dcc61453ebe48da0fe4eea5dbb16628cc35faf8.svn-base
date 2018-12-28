<template>
  <div :class="classes" :style="style"> 
    <table v-if="canvas.layout == 'ver'" cellpadding="0" cellspacing="0" style="table-layout:fixed">  
      <tr>
        <td>
          <div :class="classesLine1">
            <div class="flowgraphnode-midline"></div>
          </div>
        </td>
        <td>
          <div :style="contentStyle">
            <div class="flowgraphnode-content" >
              <div class="flowgraphnode-icon" @click.stop ="click">
                <Icon type="android-person"></Icon>
              </div>
              <div class="flowgraphnode-title">{{title}}</div>
            </div>
          </div>
        </td>
        <td style="position: relative;">
          <div :class="classesLine2" :style="line2Style" > 
            <div class="flowgraphnode-midline"></div>
          </div>
          <div class="flowgraphnode-line2-add" v-if="type=='split' && this.canvas.editable" @click.stop ="click">
            <Icon type="android-add-circle"></Icon>
          </div>
        </td>
      </tr> 
    </table>
    <table v-else cellpadding="0" cellspacing="0" style="table-layout:fixed">  <tr>
        <td>
          <div :class="classesLine1">
            <div class="flowgraphnode-midline"></div>
          </div>
        </td>
      </tr>
      <tr> 
        <td>
          <div :style="contentStyle">
            <div class="flowgraphnode-content">
              <div class="flowgraphnode-icon" @click.stop ="click">
                <Icon type="android-person"></Icon>
              </div>
              <div class="flowgraphnode-title">{{title}}</div>
            </div>
          </div>
        </td>
      </tr> 
      <tr>
        <td style="position: relative;"> 
          <div :class="classesLine2" :style="line2Style" >
            <div class="flowgraphnode-midline"></div>
          </div>
          <div class="flowgraphnode-line2-add" v-if="type=='split'" @click.stop ="click"><Icon type="android-add-circle"></Icon></div>
        </td>
      </tr>
    </table>
  </div>
</template>
<script>

export default {
  name:'flowgraphnode',
  props:{
    line1:{
      type:Number,
      default:0 //0. 1. 2. 3.
    },
    line2:{
      type:Number,
      default:1 //0. 1. 2. 3.
    }, 
    line2Depth:{
      type:Number,
      default:0.32
    }, 
    contentDepth:{
      type:Number,
      default:0.68
    }, 
    contentBreadth:{
      type:Number,
      default:1
    }, 
    title:{
      type:String,
      default:'标题'
    },
    type:{
      type:String,
      default:'person'
    },
    x:{
      type:Number,
      default:0
    },
    y:{
      type:Number,
      default:0
    },
    model:{
      type:Object,
      default:null
    },
    canvas:{
      type:Object,
      default:function(){
        return {
          layout:'ver',//ver hor
          editable:true
        };
      }
    }
  },
  data() {
    return {

    }
  },
  mounted:function(){

  },
  computed:{
    classes(){
      return [
          'flowgraphnode',
          'flowgraphnode-' + this.type,
          'flowgraphnode-' + this.canvas.layout,
          this.canvas.editable?'flowgraphnode-editable':'',
        ]; 
    },
    classesLine1(){
      return [
          'flowgraphnode-line1-' + this.line1
        ];
    },
    classesLine2(){
      return [
          'flowgraphnode-line2-' + this.line2
        ];
    },
    iconClasses(){
      
    },
    style(){
      var obj = {
        left: this.x * 100 + 'px',
        top: this.y * 100 + 'px',
      }
      if(this.canvas.layout != 'ver'){
        obj.left = this.y * 100 + 'px';
        obj.top = this.x * 100 + 'px';
      }
      return obj;
    },
    line2Style(){
      var obj = {}; 
      if(this.canvas.layout == 'ver'){
        obj.width = (this.line2Depth * 100) + 'px';  
      }else{
        obj.height = (this.line2Depth * 100) + 'px'; 
      }
      return obj;
    },
    contentStyle(){
      var obj = {};
      if(this.canvas.layout == 'ver'){
        obj.width = (this.contentDepth * 100) + 'px';
        if(this.type=='split'){
          obj.height = (this.contentBreadth * 100) + 'px';  
        }
      }else{
        obj.height = (this.contentDepth * 100) + 'px';
        if(this.type=='split'){
          obj.width = (this.contentBreadth * 100) + 'px';  
        }
      } 
      return obj;
    }
  },
  methods:{
    click:function(){
      this.$emit('click',this);
    }
  }
}

</script>

<style type="text/css">
  .flowgraphnode{
    height: 100px;
    width: auto; 
    position: absolute;
  }
  .flowgraphnode-content{
    height: 68px;
    width: 68px; 
    margin: 16px 0px; 
  }
  .flowgraphnode-icon{
    width: 48px;
    height: 48px;
    border-radius:24px;
    background-color: #5990cf;
    margin: 0px auto;
    line-height: 48px;
    font-size: 36px;
    color:white;cursor: pointer;
  }

  .flowgraphnode-line2-add{
    height: 16px;width: 16px;
    font-size: 16px;
    color:#ccc;
    background-color: white; 
    position: absolute;
    cursor: pointer;
    z-index: 50;
  }
  .flowgraphnode-line2-add:hover{
    color:#5990cf;
  }

  .flowgraphnode-title{
    text-align: center; 
  }
  
  .flowgraphnode-ver .flowgraphnode-line1-1{
    border-top: 2px solid #ccc;
    width: 32px; 
  }
  .flowgraphnode-ver .flowgraphnode-line1-2{
    border-top: 2px solid #ccc;
    border-left: 2px solid #ccc;
    height: 50px;
    margin-top: 50px;
    width: 32px; 
  }
  .flowgraphnode-ver .flowgraphnode-line1-3{ 
    border-left: 2px solid #ccc;
    height: 100px;
    width: 32px; 
  }
  .flowgraphnode-ver .flowgraphnode-line1-3 .flowgraphnode-midline{ 
    border-bottom: 2px solid #ccc;
    height: 51px;
    width: 100%;
  } 
  .flowgraphnode-ver .flowgraphnode-line1-4{
    border-bottom: 2px solid #ccc;
    border-left: 2px solid #ccc;
    height: 50px;
    margin-bottom: 50px;
    width: 32px; 
  }

  .flowgraphnode-ver .flowgraphnode-line2-1{
    border-top: 2px solid #ccc;
    width: 32px; 
  }
  .flowgraphnode-ver .flowgraphnode-line2-2{
    border-top: 2px solid #ccc;
    border-right: 2px solid #ccc;
    height: 50px;
    margin-top: 50px;
    width: 32px;
  }
  .flowgraphnode-ver .flowgraphnode-line2-3{
    border-bottom: 0px solid #ccc;
    border-right: 2px solid #ccc;
    height: 100px;
    width: 32px; 
  }
  .flowgraphnode-ver .flowgraphnode-line2-3 .flowgraphnode-midline{ 
    border-bottom: 2px solid #ccc;
    height: 51px;
    width: 100%;
  } 
  .flowgraphnode-ver .flowgraphnode-line2-4{
    border-bottom: 2px solid #ccc;
    border-right: 2px solid #ccc;
    height: 50px;
    margin-bottom: 50px;
    width: 32px; 
  }
  .flowgraphnode-ver .flowgraphnode-line2-add{
    top: 50%;
    left:-8px;
    margin-top: -8px;
  }

  .flowgraphnode-ver.flowgraphnode-split{border:0px solid red;}
  .flowgraphnode-ver.flowgraphnode-split .flowgraphnode-content{display: none;}

  /*canvas.layout = hor*/

  .flowgraphnode-hor{
    width: 68px;
    height: auto;
    position: absolute;
  }

  .flowgraphnode-hor .flowgraphnode-content{
    height: 68px;
    width: 68px; 
    margin: 0px 16px; 
  }
  
  .flowgraphnode-hor .flowgraphnode-line1-1{
    border-left: 2px solid #ccc;
    height: 32px;
    width: 0px;
    margin: 0px auto;
  }
  .flowgraphnode-hor .flowgraphnode-line1-2{
    border-left: 2px solid #ccc;
    border-top: 2px solid #ccc;
    width: 50px;
    margin-left: 50%;
    height: 32px; 
  }
  .flowgraphnode-hor .flowgraphnode-line1-3{ 
    border-top: 2px solid #ccc;
    width: 100px;
    height: 32px; 
  }
  .flowgraphnode-hor .flowgraphnode-line1-3 .flowgraphnode-midline{ 
    border-right: 2px solid #ccc;
    width: 51px;
    height: 100%;
  } 
  .flowgraphnode-hor .flowgraphnode-line1-4{
    border-top: 2px solid #ccc;
    border-right: 2px solid #ccc;
    width: 50px;
    height: 32px; 
    float: right;
    margin-right: 50%;
  }

  .flowgraphnode-hor .flowgraphnode-line2-1{
    border-left: 2px solid #ccc;
    height: 32px;
    width: 0px;
    margin: 0px auto;
  }
  .flowgraphnode-hor .flowgraphnode-line2-2{
    border-left: 2px solid #ccc;
    border-bottom: 2px solid #ccc;
    width: 50px;
    margin-left: 50%;
    height: 32px; 
  }
  .flowgraphnode-hor .flowgraphnode-line2-3{
    border-bottom: 2px solid #ccc;
    width: 100px;
    height: 32px;
  }
  .flowgraphnode-hor .flowgraphnode-line2-3 .flowgraphnode-midline{ 
    border-right: 2px solid #ccc;
    height: 100%;
    width: 51px;
  } 
  .flowgraphnode-hor .flowgraphnode-line2-4{
    border-bottom: 2px solid #ccc;
    border-right: 2px solid #ccc;
    width: 50px; 
    height: 32px; 
    float: right;
    margin-right: 50%;
  }
  .flowgraphnode-hor .flowgraphnode-line2-add{ 
    position: absolute;
    top:-8px;
    left: 50%;
    margin-left: -8px;
  }
  .flowgraphnode-hor.flowgraphnode-split{border:0px solid red;}
  .flowgraphnode-hor.flowgraphnode-split .flowgraphnode-content{display: none;}

  /*结点图标颜色*/ 
  .flowgraphnode-start .flowgraphnode-icon{
    background-color: #009900;
  }
  .flowgraphnode-end .flowgraphnode-icon{
    background-color: #ccc; 
  }
  .flowgraphnode-end .flowgraphnode-icon{
    font-size: 20px;
  }
  .flowgraphnode-end .flowgraphnode-icon .ivu-icon:before,
  .flowgraphnode-end .flowgraphnode-icon:hover .ivu-icon:before {
    content: "\F24F";  font-size: 20px;
  }
  .flowgraphnode-role .flowgraphnode-icon .ivu-icon:before {
    content: "\F39E";  
  }
  .flowgraphnode-department .flowgraphnode-icon .ivu-icon:before {
    content: "\F139";  
  }
  .flowgraphnode-editable .flowgraphnode-icon:hover .ivu-icon:before {
    content: "\F2BF";
    font-size: 34px;
  } 

  
</style>