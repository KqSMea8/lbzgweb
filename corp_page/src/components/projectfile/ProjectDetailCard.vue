<template>
  <div class="inquire-detail-card editable">
    <div class="title">{{title}}</div>
    <div class="editable-table">
      <table cellspacing="0" cellpadding="0">
        <thead>
          <th>图号</th>
          <th>物料名称</th>
          <th width="60">材质</th>
          <th width="60">硬度</th>
          <th width="80">交货类型</th>
          <th width="80">单位</th>
          <th width="80">数量</th>
          <th width="80">总重</th>
          <th width="110">交货日期</th>
          <th>备注</th>
        </thead>
        <tbody>
          <tr>
            <td>
              {{material.drawing}}
            </td>
            <td>
              {{material.materName}}
            </td>
            <td class="col-cz">
              {{material.texture}}
            </td>
            <td class="col-yd">
              {{material.hardness}}
            </td>
            <td>
              {{material.deliveryType}}
            </td>
            <td>
              {{material.unit}}
            </td>
            <td>
              {{material.quantity}}
            </td>
            <td>
              {{material.weightTotal}}
            </td>
            <td>
              {{material.deliveryDate}}
            </td>
            <td>
              {{material.remark}}
            </td>
          </tr>
        </tbody>
      </table>
      <table v-if="editable" class="editable-card" cellspacing="0" cellpadding="0">
        <tr>
          <th>净重（kg）</th>
          <td><Input v-model="fields['净重（kg）']"></Input></td>
          <th>毛重（kg）</th>
          <td><Input v-model="fields['毛重（kg）']"></Input></td>
          <th>补块重量（kg）</th>
          <td><Input v-model="fields['补块重量（kg）']"></Input></td>
          <th>合同总重（kg）</th>
          <td><Input v-model="fields['合同总重（kg）']"></Input></td>
        </tr>
        <tr>
          <th>UT标准</th>
          <td><Input v-model="fields['UT标准']"></Input></td>
          <th>UT等级</th>
          <td><Input v-model="fields['UT等级']"></Input></td>
          <th>MT标准</th>
          <td><Input v-model="fields['MT标准']"></Input></td>
          <th>MT等级</th>
          <td><Input v-model="fields['MT等级']"></Input></td>
        </tr>
        <tr>
          <th>PT标准</th>
          <td><Input v-model="fields['PT标准']"></Input></td>
          <th>PT等级</th>
          <td><Input v-model="fields['PT等级']"></Input></td>
          <th>RT标准</th>
          <td><Input v-model="fields['RT标准']"></Input></td>
          <th>RT等级</th>
          <td><Input v-model="fields['RT等级']"></Input></td>
        </tr>
        <tr>
          <th>毛坯尺寸公差标准</th>
          <td><Input v-model="fields['毛坯尺寸公差标准']"></Input></td>
          <th>毛坯尺寸公差等级</th>
          <td><Input v-model="fields['毛坯尺寸公差等级']"></Input></td>
          <th>化学成份及性能标准</th>
          <td><Input v-model="fields['化学成份及性能标准']"></Input></td>
          <th>表面粗糙度标准等级</th>
          <td><Input v-model="fields['表面粗糙度标准等级']"></Input></td>
        </tr>
        <tr>
          <th>热处理方式</th>
          <td><Input v-model="fields['热处理方式']"></Input></td>
          <th>硬度要求</th>
          <td><Input v-model="fields['硬度要求']"></Input></td>
          <th>试棒要求</th>
          <td><Input v-model="fields['试棒要求']"></Input></td>
          <th>交货状态</th>
          <td><Input v-model="fields['交货状态']"></Input></td>
        </tr>
        <tr>
          <th>备注</th>
          <td colspan="7">
            <Input v-model="fields['备注']"></Input>
          </td>
        </tr>
      </table>
      <table v-else class="editable-card" cellspacing="0" cellpadding="0">
        <tr>
          <th>净重（kg）</th>
          <td> {{fields['净重（kg）']}} </td>
          <th>毛重（kg）</th>
          <td>{{fields['毛重（kg）']}}</td>
          <th>补块重量（kg）</th>
          <td>{{fields['补块重量（kg）']}}</td>
          <th>合同总重（kg）</th>
          <td>{{fields['合同总重（kg）']}}</td>
        </tr>
        <tr>
          <th>UT标准</th>
          <td>{{fields['UT标准']}}</td>
          <th>UT等级</th>
          <td>{{fields['UT等级']}}</td>
          <th>MT标准</th>
          <td>{{fields['MT标准']}}</td>
          <th>MT等级</th>
          <td>{{fields['MT等级']}}</td>
        </tr>
        <tr>
          <th>PT标准</th>
          <td>{{fields['PT标准']}}</td>
          <th>PT等级</th>
          <td>{{fields['PT等级']}}</td>
          <th>RT标准</th>
          <td>{{fields['RT标准']}}</td>
          <th>RT等级</th>
          <td>{{fields['RT等级']}}</td>
        </tr>
        <tr>
          <th>毛坯尺寸公差标准</th>
          <td>{{fields['毛坯尺寸公差标准']}}</td>
          <th>毛坯尺寸公差等级</th>
          <td>{{fields['毛坯尺寸公差等级']}}</td>
          <th>化学成份及性能标准</th>
          <td>{{fields['化学成份及性能标准']}}</td>
          <th>表面粗糙度标准等级</th>
          <td>{{fields['表面粗糙度标准等级']}}</td>
        </tr>
        <tr>
          <th>热处理方式</th>
          <td>{{fields['热处理方式']}}</td>
          <th>硬度要求</th>
          <td>{{fields['硬度要求']}}</td>
          <th>试棒要求</th>
          <td>{{fields['试棒要求']}}</td>
          <th>交货状态</th>
          <td>{{fields['交货状态']}}</td>
        </tr>
        <tr>
          <th>备注</th>
          <td colspan="7">{{fields['备注']}}</td>
        </tr>
      </table>
    </div>
  </div>
</template>
<script>
  export default {
    components: {

    },
    props:{
      title:{
        type:String,
        default:'物料1'
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
        }
      },
      editable:{
        type:Boolean,
        default:false
      }
    },
    data() {
      return {
        fields:this.loadFields()
      }
    },
    mounted: function () {

    },
    watch:{
      material(val,old){
        if(val != old){
          this.fields = this.loadFields();
        }
      }
    },
    computed: {},
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
</style>
