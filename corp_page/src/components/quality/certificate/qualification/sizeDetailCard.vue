<template>
   <div class="inquire-detail-card editable">

     <div class="editable-table">
       <table cellspacing="0" cellpadding="0">
         <tr>
           <td colspan="3">零件加工尺寸报告<br/> Machining Inspection Report</td>
           <td colspan="3">
             <img :src="require('@/assets/img/sizeLogo.png')" style="vertical-align: middle;"/>
           </td>
         </tr>
        <tr>
           <td>零件名称：<br/>Part Name</td>
           <td>{{ queryForm.materName }}</td>
           <td>铸造号码:<br/> Senal Number</td>
           <td> {{ queryForm.senalNumber }}</td>
           <td>"材质<br/> Material"</td>
           <td>{{ queryForm.material }}</td>
         </tr>
         <tr>
           <td>零件图号： <br/>Drawing Number</td>
           <td>{{ queryForm.drawingNumber }}</td>
           <td>零件状态：<br/>Part Condition</td>
           <td colspan="3">{{ queryForm.partCondition }}</td>
         </tr>
         <tr>
           <td colspan="12">

               <Scroll :on-reach-bottom="handleReachBottom" style="width: 100%;!important;">
                 <table cellspacing="0" cellpadding="0">
                   <thead>
                   <tr style="width: 1000%">
                     <th>序号 ：<br/>No.</th>
                     <th>图纸尺寸:<br/> Dimension</th>
                     <th>"区域<br/> Area"</th>
                     <th>公差/余量<br/> Tolerance/ Allowance</th>
                     <th>实际尺寸<br/> Actual Dimension</th>
                   </tr>
                   </thead>

                   <tr dis-hover v-for="(item, index) in list1" :key="index">
                     <td style="width: 200px;!important;">{{ material.inquireId }}+{{ index }}}</td>
                     <td style="width: 100%;!important;">{{ material.inquireId }}</td>
                     <td style="width: 100%;!important;">{{ material.inquireId }}</td>
                     <td style="width: 100%;!important;">{{ material.inquireId }}</td>
                     <td style="width: 100%;!important;">{{ material.inquireId }}</td>
                   </tr>
                 </table>
               </Scroll>

           </td>
         </tr>
         <tr>
           <td>备     注   <br/>Remark</td>
           <td colspan="5"><Input v-model="queryForm.remark" :readonly="isList!=2" placeholder="备     注" ></Input></td>
         </tr>
         <tr>
           <td>结论/Conclusion：</td>
           <td colspan="5"><Input v-model="queryForm.conclusion" :readonly="isList!=2" placeholder="结论" ></Input></td>
         </tr>
         <tr>
           <td>检验/Inspector:</td>
           <td>{{ queryForm.inspector }}</td>
           <td>审核/Reviewed:</td>
           <td colspan="3">{{ queryForm.reviewed }}</td>
         </tr>
         <tr>
           <td>日期/Date:</td>
           <td>{{ queryForm.createTime }}</td>
           <td>日期/Date:</td>
           <td colspan="1">{{ queryForm.wfInstTime }}</td>
           <td colspan="2"> &nbsp;&nbsp;&nbsp;&nbsp;共页 &nbsp;&nbsp;&nbsp;&nbsp;第页 &nbsp;&nbsp;&nbsp;&nbsp;</td>
         </tr>
         <!--<tr>
           <td colspan="2">备     注:   <br/>Remark:<textarea rows="" cols="20">

            </textarea></td>
           <td colspan="2">审核:   <br/> checked by：<textarea rows="" cols="20">

            </textarea></td>
           <td colspan="2">批准： <br/> Approved by：<textarea rows="" cols="20">

            </textarea></td>
         </tr>-->
       </table>
     </div>
   </div>
</template>
<script>
  export default {
    components: {

    },
    data(){
      return {
          list1:{
              materName:'',
              senalNumber:'',
              material:'',
              drawingNumber:'',
              partCondition:'',
              remark:'',
              conclusion:'',
              reviewed:'',
              inspector:'',
              operator:'',
              createTime:'',
              wfInstTime:'',
              contractId:'',
              createTime:'',
          },
      }
    },
    props:{
      title:{
        type:String,
        default:'零件加工尺寸报告'
      },

      queryForm:{
        type:Object,
        default:function () {
          return {
              materName:'',
              partName:'',
              senalNumber:'',
              material:'',
              drawingNumber:'',
              partCondition:'',
              remark:'',
              status:0,
              conclusion:'',
              reviewed:'',
              createTime:'',
              wfInstTime:'',
              contractId:'',
          };
        },

      },
      isList:{
         type:Number,
         default:2
      },
      editable:{
        type:Boolean,
        default:false
      }
    },
    data() {
      return {

      }
    },
    mounted: function () {
        this.list1={
            id:0,
            inquireId:'',
            materId:'',
            drawing:'',
            materName:'',
            texture:'',
            hardness:'',
            deliveryType:'',
            unit:'',
            quantity:0,
            weight:0,
            weightTotal:0,
            deliveryDate:null,
            remark:'',
            fieldJson:'',
        };
    },
    watch:{
    },
    computed: {
      deliveryTypeName: function () {
        return this.$args.getArgText("process_require",this.material.deliveryType)
      },
      unitName: function (){
        return this.$args.getArgText("unit",this.material.unit)
      }
    },
    methods: {
      load() {
      },
      handleReachBottom () {
        return new Promise(resolve => {
          setTimeout(() => {
            const last = this.list1[this.list1.length - 1];
            for (let i = 1; i < this.list1.length; i++) {
              this.list1.push(last + i);
            }
            resolve();
          }, 2000);
        });
      },
    }
  }

</script>

<style type="text/css">
  .inquire-detail-card.editable {

  }
  .inquire-detail-card .title{
    height: 40px;
    line-height: 38px;
    text-align: center;
    min-width: 89px;
    border: 1px solid #dedede;
    display: inline-block;
    border-bottom: 0px;font-weight: bold;
  }
  .inquire-detail-card .editable-table{
    overflow-x: auto;
  }
  .editable-table table{
    border-collapse:collapse;
    border: 1px solid #dedede;
    width: 100%;
  }
  .editable-table table th{
    background: #efefef;
  }
  .inquire-detail-card .editable-table table td,
  .inquire-detail-card .editable-table table th{
    height: 40px;
    border: 1px solid #dedede;
    padding: 0 10px;text-align: center;
  }
  .editable-table .ivu-input,
  .editable-table .ivu-input-number,
  .editable-table .ivu-select,
  .editable-table .ivu-select-selection{
    border: 0px;
  }
  .editable-table .ivu-input-number-input{
    padding-right: 24px;
    text-align: right;
  }
  .editable-table .col-xh{
    text-align: center; cursor: default;
  }
  .editable-table .col-xh.cur{
    background: #e8f8fd;
    color:#20bfee;
    border-right: 2px solid #20bfee;
    cursor: default;font-weight: bold;;
  }
  /*行扩展操作*/
  .editable-table .col-cz input{
    text-align: center;;
  }
  .editable-table .col-yd input{
    text-align: center;;
  }
  .inquire-detail-card .editable-card{
    margin-top: 10px;
    margin-bottom: 10px;
  }
/*
*/
  .d1{border-top:80px threedlightshadow solid;border-left:100px windowframe solid;width:0;height:0;position:relative;color:#FFF}

  .ss{display:block;width:40px}

  .s1{position:absolute;top:-70px;left:-44px;color:black;}

  .s2{position:absolute;bottom:8px;right:55px}


</style>
