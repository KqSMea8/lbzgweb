<template>
   <div class="inquire-detail-card editable">

     <div class="editable-table">
       <table cellspacing="0" cellpadding="0">

         <thead>
         <tr>
           <td colspan="7" style="text-align: right;!important;">{{ queryForm.nowDate }}</td>
         </tr>
         <tr>
           <th>名称</th>
           <th>图号</th>
           <th>材质</th>
           <th>炉号</th>
           <th>单重（kg）</th>
           <th>数量（件）</th>
           <th>铸件编号</th>
         </tr>
         </thead>
         <tbody>
           <tr v-for="(item3,index3) in material">
             <td>
               {{item3.materName}}
             </td>
             <td>
               {{item3.drawing}}
             </td>
             <td class="col-cz">
               {{item3.texture}}
             </td>
             <td class="col-yd">
               {{item3.heatNumber}}
             </td>
             <td>
               {{item3.pieceWeight}}
             </td>
             <td>
               {{item3.quantity}}
             </td>
             <td>
               {{item3.castingNumber}}
             </td>
           </tr>
         </tbody>
       </table>
      <!-- <table v-if="editable" class="editable-card" cellspacing="0" cellpadding="0">-->
       <table class="editable-card" cellspacing="0" cellpadding="0" style="margin-top: 0px;border-top: 0px;!important;">
         <tr style="text-align: center">
           <td colspan="7">
             化学成份%
           </td>

         </tr>
         <tr>
           <th rowspan="2" style="width:100px;padding: 0px 0px;!important;">
             <div class="d1">

               <span class="s1 ss">标准</span>

               <span class="s2 ss">炉号</span>

             </div>

           </th>
           <th>
           C
         </th>
           <th>
             Mn
           </th>
           <th>
             Si
           </th>
           <th>
             S
           </th>
           <th>
             P
           </th>
           <th style="padding: 0px 5px">
             化验员：
           </th>
         </tr>
         <tr>
           <td>{{fields['毛重（kg）']}}</td>
           <td>{{fields['毛重（kg）']}}</td>
           <td>{{fields['毛重（kg）']}}</td>
           <td>{{fields['毛重（kg）']}}</td>
           <td>{{fields['毛重（kg）']}}</td>
           <td rowspan="3">{{fields['毛重（kg）']}}</td>
         </tr>
         <tr>
           <td>{{fields['毛重（kg）']}}</td>
           <td>{{fields['毛重（kg）']}}</td>
           <td>{{fields['毛重（kg）']}}</td>
           <td>{{fields['毛重（kg）']}}</td>
           <td>{{fields['毛重（kg）']}}</td>
           <td>{{fields['毛重（kg）']}}</td>
         </tr>
         <tr>
           <td>{{fields['毛重（kg）']}}</td>
           <td>{{fields['毛重（kg）']}}</td>
           <td>{{fields['毛重（kg）']}}</td>
           <td>{{fields['毛重（kg）']}}</td>
           <td>{{fields['毛重（kg）']}}</td>
           <td>{{fields['毛重（kg）']}}</td>
         </tr>
       </table>

         <table class="editable-card" cellspacing="0" cellpadding="0" style="margin-top: -10px;border-top: 0px;!important;">
         <tr style="text-align: center">
           <td colspan="8">
             力学性能
           </td>

         </tr>
         <tr>
           <th rowspan="2" style="width:100px;padding: 0px 0px;!important;">
             <div class="d1">

               <span class="s1 ss">标准</span>

               <span class="s2 ss">编号</span>

             </div>

           </th>
           <th>
             屈服强度
             ReL/MPa
           </th>
           <th>
             抗拉强度
             Rm/MPa
           </th>
           <th>
             断后伸长率
             A/%
           </th>
           <th>
             断面收缩率
             Z/%
           </th>
           <th>
             冲击吸收功
             Aku /J
           </th>
           <th>
             硬度
             HB
           </th>
           <th style="padding: 0px 5px">
             化验员：
           </th>
         </tr>
         <tr>
           <td>{{fields['毛重（kg）']}}</td>
           <td>{{fields['毛重（kg）']}}</td>
           <td>{{fields['毛重（kg）']}}</td>
           <td>{{fields['毛重（kg）']}}</td>
           <td>{{fields['毛重（kg）']}}</td>
           <td>{{fields['毛重（kg）']}}</td>
           <td rowspan="3">{{fields['毛重（kg）']}}</td>
         </tr>
         <tr>
           <td>{{fields['毛重（kg）']}}</td>
           <td>{{fields['毛重（kg）']}}</td>
           <td>{{fields['毛重（kg）']}}</td>
           <td>{{fields['毛重（kg）']}}</td>
           <td>{{fields['毛重（kg）']}}</td>
           <td style="padding: 0"><table><tr><td>{{fields['毛重（kg）']}}</td><td>{{fields['毛重（kg）']}}</td><td>{{fields['毛重（kg）']}}</td></tr></table></td>
           <td>{{fields['毛重（kg）']}}</td>
         </tr>
         <tr>
           <td>{{fields['毛重（kg）']}}</td>
           <td>{{fields['毛重（kg）']}}</td>
           <td>{{fields['毛重（kg）']}}</td>
           <td>{{fields['毛重（kg）']}}</td>
           <td>{{fields['毛重（kg）']}}</td>
           <td style="padding: 0"><table><tr><td>{{fields['毛重（kg）']}}</td><td>{{fields['毛重（kg）']}}</td><td>{{fields['毛重（kg）']}}</td></tr></table></td>
           <td>{{fields['毛重（kg）']}}</td>
         </tr>
         </table>

       <table class="editable-card" cellspacing="0" cellpadding="0" style="margin-top:-10px;border-top: 0px;!important;">
       <tr>
         <th>结论</th>
         <td v-show="queryForm.status==0"> 合格</td>
         <td v-show="queryForm.status==3"> 不合格</td>
         <th>质检员</th>
         <td>{{queryForm.testPeople}}</td>
         <th>审核</th>
         <td>{{queryForm.checkedBy}}</td>
         <th>批准</th>
         <td>{{queryForm.approvedBy}}</td>
       </tr>
       </table>
     </div>
   </div>
</template>
<script>
    import page from '@/assets/js/page';
  export default {
    components: {
        page,
    },
    props:{
      title:{
        type:String,
        default:'铸件合格证'
      },
      material:{
        type:Object,
        default:function () {
          return {
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

      },
        queryForm: {
            type: Object,
            default: function () {
                return {
                    status:"1",
                    audit:'',
                    proposer:'',
                    nowDate:'',
                };
            }
        },
      editable:{
        type:Boolean,
        default:false
      }
    },
    data() {
      return {
        fields:this.loadFields(),
      }
    },
    mounted: function () {
        this.load();
    },
    watch:{
      material(val,old){
        if(val != old){
          this.fields = this.loadFields();
        }
      }
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
      loadFields(){
        var fields = {};
        if(this.material.fieldJson
          && this.material.fieldJson.length > 0
          && this.material.fieldJson[0]=='{'){
          try{
            fields = eval("("+ this.material.fieldJson +")");
          }catch(ex){
            console.log('转换 json 数据失败！');
          }
        }
        return fields;
      }
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
