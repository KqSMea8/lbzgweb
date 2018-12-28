<template> 
  <Editable @add="add" @remove="remove" :editable="editable"><!--添加页面-->
    <table cellspacing="0" cellpadding="0" v-if="!editable">
      <thead>

        <th >物料编码</th>
        <th >物料名称</th>
        <th >规格(图号)</th>
        <th >型号（材质）</th>

        <th><span>单价（毛重）</span></th>
        <th ><span>数量（件数）</span></th>
        <th ><span>金额（顺序号）</span></th>

        <th><span>备注</span></th>
        <th><span>备注（不合格原因）</span></th>
      </thead>
      <tbody>
        <tr v-for="(item,index) in list"  :key="'mater_'+index" @click="curIndex = index">
          <td :class="{'col-xh':true,'cur':index==curIndex}">
            {{index+1}}
          </td>
          <td>
            {{item.materId}}
          </td>
          <td >
            {{item.materName}} 
          </td>
          <td >
            {{item.spec}} 
          </td>
          <td >
            {{item.model}} 
          </td>
          <td >
            {{$args.getArgText('material_subtype',item.subType)}} 
          </td> 
          <td >
            {{$args.getArgText('unit',item.unit)}}  
          </td>
          <td>
            {{item.price}}
          </td>
          <td> 
            {{item.quantity}}
          </td>
          <td> 
            {{item.amount}}
          </td>
          <td>
            {{item.use}} 
          </td>
          <td > 
            {{item.providerName}}
          </td>
          <td>
            {{item.origin}}
          </td>
         <!-- <td>
            {{item.needDate}}
          </td>
          <td>
            {{item.remark}} 
          </td>-->
        </tr>
      </tbody>
    </table>

    <table cellspacing="0" cellpadding="0" v-else>
        <thead>
          <th class="col-xh">序号</th>
          <th >物料编码</th>
          <th >物料名称</th>
          <th >规格(图号)</th>
          <th >型号(材质)</th>
          <!--<th >采购类别</th> -->
          <th >单位（毛重）</th>
          <!--<th class="col-price"><span>单价</span></th>-->
          <th class="col-quantity"><span>数量(件数)</span></th>
          <th class="col-amount"><span>金额（顺序号）</span></th>
        <!--  <th class="col-w3"><span>用途</span></th>
          <th >供应商</th>
          <th >供方或产地</th>
          <th class="col-date"><span>需求日期</span></th>-->
          <th class="col-remark"><span>备注</span></th>
          <th class="col-remark"><span>不合格原因</span></th>
        </thead> 
        <tbody>
          <tr v-for="(item,index) in list"  :key="'mater_'+index" @click="curIndex = index">
            <td :class="{'col-xh':true,'cur':index==curIndex}">
              {{index+1}}
            </td>

            <!--<td class="col-select" @click="editable && selMater(item)"    >
              <span>{{item.materId}}</span>
            </td>-->
            <td class="col-select"  @click="editable && selMater(item)">
            {{item.materId}}
            </td>
            <td >
              {{item.materName}} <!--//图号-->
            </td>
            <td >
              {{item.spec}} 
            </td>
            <td >
              {{item.model}} <!--毛重-->
            </td>
            <!--<td >
              {{$args.getArgText('material_subtype',item.subType)}}
            </td>-->
            <td class="col-afterPrice">
              <Input  type="text" v-model="item.subType" />
            </td>
            <!--<td >
              {{$args.getArgText('unit',item.unit)}}  金额
            </td>-->
           <!--&lt;!&ndash; <td class="col-price">
              {{item.price}}
            </td>-->
            <td class="col-quantity">
              <InputNumber  :max="999999" :min="0" v-model="item.quantity" @on-change="computedAmount(item)"></InputNumber> 
            </td>
            <!--<td class="col-amount">
              {{item.amount}}
            </td>-->
            <td class="col-afterPrice">
              <Input  type="text" v-model="item.amount" />
            </td>
            <!--<td>
               <Input type="text" v-model="item.use" /> 
            </td>&ndash;&gt;&ndash;&gt;-->
            <!--<td >
              {{item.providerName}}
            </td>-->
            <td class="col-afterPrice">
              <Input  type="text" v-model="item.providerName" />
            </td>
            <td>
              {{item.origin}}
            </td>
           <!-- <td>
              {{item.origin}}&lt;!&ndash;不合格原因&ndash;&gt;
            </td>-->
           <!-- <td class="col-date">
              <DatePicker  type="date" placeholder="需求日期" v-model="item.needDate" @on-change="datePickerChange(item,arguments)"></DatePicker>  
            </td>
            <td class="col-remark">
              <Input  type="text" v-model="item.remark" />
            </td>-->
          </tr>
        </tbody>
      </table>
    <SelectMaterial ref="selmaterial" :transfer="true"></SelectMaterial>
  </Editable>  
</template>
<script>
  import Editable from '@/components/editable-table';
  import floatObj from '@/assets/js/floatObj';
  import SelectMaterial from '@/components/purchase/provider-material/SelProviderMaterial'

  export default {
    components: { 
      SelectMaterial,
      Editable 
    },
    props:{
      list:{
        type:Array,
        default:function () {
          var arr = [];
        }
      },
      editable:{
        type:Boolean,
        default:false
      }
    },
    data() { 
      return {
        curIndex:0,
      }
    },
    mounted: function () {
      
    },
    computed: {},
    methods: { 
      load() {

      }, 
      listNewRow(){
        var def = {
          id:0,
          materId:'',
          subType:'',
          materName:'',
          level:0,
          spec:'',
          model:'',
          use:'',
          providerId:'',
          providerName:'',
          origin:'',
          unit:'',
          needDate:'',
          price:0,
          quantity:0,
          amount:0,
          remark:''
        };
        return def;
      },
      add(){
        this.list.push(this.listNewRow());
      },
      remove(){
        if(this.list.length > this.curIndex){
          this.list.splice(this.curIndex,1);
        }
        if(this.list.length <= this.curIndex){
          this.curIndex = this.list.length - 1;
        }
        if(this.curIndex == -1){
          this.curIndex = 0;
        }
      },
      datePickerChange(item,args){
        item.needDate = args[0];
      },
      computedAmount(item){ 
        item.amount = floatObj.multiply(item.price,item.quantity);  
        var total = 0;
        this.list.map((mater)=>{
          total = floatObj.add(total,mater.amount);
        });
        this.$emit('on-amount-change',total);
      },
      selMater(row){
        var selmaterial = this.$refs.selmaterial;
        selmaterial.show({
          ok:(data)=>{ 
            if(_.findIndex(this.list, { 'materId': data.materId })>=0){
              this.$Message.error('物料已存在!');
              return ;
            }
            row.materId = data.materId;
            row.materName = data.materName;

            row.spec = data.spec;
            row.model = data.model;
            row.unit = data.unit;
            row.price = data.price;
            row.subType = data.subType;
            row.providerId = data.providerId;
            row.providerName = data.providerName;
            row.origin = data.origin;
            row.use = data.use;
            this.computedAmount(row);
          }
        });
      },
    }
  }

</script>

<style type="text/css"> 
   
</style>
