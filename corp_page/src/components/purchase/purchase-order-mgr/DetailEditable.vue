<template> 
  <Editable @add="add" @remove="remove" :editable="editable">
      <table cellspacing="0" cellpadding="0" v-if="!editable">
        <thead>
          <th class="col-xh">序号</th> 
          <th >物料编码</th>
          <th>物料名称</th>
          <th >规格</th>
          <th >型号</th>
          <th >单位</th>
          <th >数量</th>
          <th >用途</th>
          <th >供方或产地</th>
          <th >供应商</th>
          <th >采购员</th>
          <th >到货日期</th>
          <th >需求日期</th>
          <th >到货数量</th>
          <th >单价</th> 
          <th >金额</th>
          <th >原厂批号</th>
          <th >厂批号</th>
          <th >生产日期</th>
          <th >有效期</th> 
          <th >质保期</th> 
          <th >备注</th> 
        </thead>
        <tbody>
           <tr v-for="(item,index) in list"  :key="'mater_'+index" @click="curIndex = index">
            <td :class="{'col-xh':true,'cur':index==curIndex}">
              {{index+1}}
            </td>
            <td>
              {{item.materId}}<!--物料编码 -->
            </td>
            <td>
              {{item.materName}}<!--物料名称 -->
            </td>
            <td>
              {{item.spec}}<!--规格 -->
            </td>
            <td>
              {{item.model}}<!--型号 -->
            </td>
            <td>
              {{item.unit}}<!-- 单位-->
            </td>
            <td> 
              {{item.quantity}}<!--申购数量  -->
            </td>
            <td> 
              {{item.use}}<!--用途 -->
            </td>
            <td> 
              {{item.origin}}<!--供方或产地 -->
            </td>
            <td> 
              {{item.providerName}}<!--供应商 -->
            </td>
            <td> 
              {{item.buyerName}}<!--采购员 -->
            </td>
            <td>
              {{item.arrivalDate}}<!-- 到货日期-->
            </td>
            <td>
               {{item.needDate}}<!--需求日期 -->
            </td>
             <td @on-change="computedAmount(item);">
               {{item.quantity}}<!-- 到货数量-->
            </td>
             <td @on-change="computedAmount(item);">
              {{item.price}}<!--单价 -->
            </td>
            <td>
              {{item.amount}}<!--金额 -->
            </td>
            <td>
              {{item.originBatch}}<!-- 原厂批号-->
            </td>
            <td> 
              {{item.batch}}<!-- 厂批号-->
            </td>
            <td> 
              {{item.madeDate}}<!--生产日期 -->
            </td>
            <td> 
              {{item.validityDate}}<!--有效期 -->
            </td>
            <td>
               {{item.qualityTime}}<!-- 质保期-->
            </td>
            <td>
              {{item.remark}}<!-- 备注-->
            </td>
          </tr>
        </tbody>
      </table>

      <table cellspacing="0" cellpadding="0" v-else>
        <thead>
          <th class="col-xh">序号</th> 
          <th >物料编码</th>
          <th >物料名称</th>
          <th >规格</th>
          <th >型号</th>
          <th >单位</th>
          <th >数量</th>
          <th >用途</th>
          <th >供方或产地</th>
          <th >供应商</th>
          <th >采购员</th>
          <th class="col-date"><span>到货日期</span></th>
          <th class="col-date"><span>需求日期</span></th>
          <th class="col-quantity">到货数量</th>
          <th class="col-price">单价</th> 
          <th class="col-amount">金额</th>
          <th >原厂批号</th>
          <th >厂批号</th>
          <th class="col-date"><span>生产日期</span></th>
          <th class="col-date"><span>有效期</span></th> 
          <th >质保期</th> 
          <th class="col-remark"><span>备注</span></th> 
        </thead>
        <tbody>
          <tr v-for="(item,index) in list"  :key="'mater_'+index" @click="curIndex = index">
            <td :class="{'col-xh':true,'cur':index==curIndex}">
              {{index+1}}
            </td>
            <td class="col-select" @click="selMater(item)"  v-if="isNew">
              <span>{{item.materId}}</span>
            </td>
            <td v-else> 
              {{item.materId}}
            </td>
            <td>
              {{item.materName}}
            </td>
            <td>
              {{item.spec}}
            </td>
            <td>
              {{item.model}}
            </td>
            <td>
              {{$args.getArgText('unit',item.unit)}}  
            </td>
            <td> 
              {{item.quantity}}
            </td>
            <td> 
              {{item.use}}
            </td> 
            <td>
              {{item.origin}} 
            </td>
            
            <td class="col-select" @click="selProvider(item)" v-if="isNew"><!--新建空白的是可以选供应商,方法selProvider(item) -->
              <span>{{item.providerName}}</span>
            </td>
            <td v-else> <!--带一部分参数值的带入的是不可以选供应商 -->
              {{item.providerName}}
            </td>

            <td class="col-select" @click="selEmp(item)">
              <span>{{item.buyerName}}</span>
            </td>
             
            <td class="col-date">
              <DatePicker type="date" placeholder="到货日期" v-model="item.arrivalDate" @on-change="item.arrivalDate=arguments[0]"></DatePicker>
            </td>
            <td class="col-date">
              <DatePicker type="date" placeholder="需求日期" v-model="item.needDate" @on-change="item.needDate=arguments[0]"></DatePicker>
            </td>
            <td class="col-quantity">
              <InputNumber  :max="999999" :min="0" v-model="item.quantity" @on-change="computedAmount(item);"></InputNumber> 
            </td>
            <td class="col-price"> 
              <InputNumber  :max="999999" :min="0" v-model="item.price" @on-change="computedAmount(item);"></InputNumber> 
            </td>
            <td class="col-amount"> 
              <InputNumber  :max="999999" :min="0" v-model="item.amount"></InputNumber> 
            </td>

            <td>
              <Input type="text" v-model="item.originBatch" />
            </td>
             <td>
              <Input type="text" v-model="item.batch" />
            </td>
            
            <td class="col-date">
              <DatePicker type="date" placeholder="生产日期" v-model="item.madeDate" @on-change="item.madeDate=arguments[0]"></DatePicker> 
            </td>
            <td class="col-date">
               <DatePicker type="date" placeholder="有效期" v-model="item.validityDate" @on-change="item.validityDate=arguments[0]"></DatePicker> 
            </td>
            <td>
              <Input type="text" v-model="item.qualityTime" />
            </td>
             <td>
              <Input type="text" v-model="item.remark" />
            </td>
          </tr>
        </tbody>
      </table> 
    <SelectMaterial ref="selmaterial" :transfer="true"></SelectMaterial>
    <SelProvider ref="selProvider"></SelProvider>
    <SelContacts ref="selContacts" :transfer="true"></SelContacts>
  </Editable> 
</template>
<script>
  import Editable from '@/components/editable-table';
  import floatObj from '@/assets/js/floatObj';
  import SelectMaterial from '@/components/purchase/provider-material/SelProviderMaterial';
  /*import SelectMaterial from '@/components/material/selmaterial/SelectMaterial';*/
  import SelProvider from '@/components/provider/SelectProvider'; 
  import SelContacts from '@/components/selcontacts'; 

  export default {
    components: { 
      SelectMaterial,
      SelProvider,
      SelContacts,
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
      },
      isNew:{//判断是否可编辑
        type:Boolean,
        default:false
      }
    },
    data() { 
      return {
        curIndex:0,
        storageId:'', 
        storages:[{
          storageId:'s0001',
          name:'仓库001'
        }],
      }
    },
    mounted: function () {
       this.loadStorage();//加载仓库数据源
    },
    computed: {},
    methods: { 
       loadStorage(){//加载仓库数据源
        this.loading = 1;
        this.$http.post("/api/storage/list", {pageSize:100}).then((res) => { 
          this.loading = 0;
          if (res.data.code === 0) {
            this.storages = res.data.data.rows;
          } else {
            this.$Message.error(res.data.message);
          }
        }).catch((error) => {
          this.loading = 0;
          this.$Message.error('加载出错！');
        });
      },
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
          remark:'',

          buyer:'',
          buyerName:'',
          arrivalDate:'',
          storageId:'', 
          storage:'',
          originBatch:'',
          batch:'',
          madeDate:'',
          validityDate:'',
          qualityTime:'',
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
    
      computedWeightTotal(item){
        item.weightTotal = item.quantity*item.weight;
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
            row.subType = data.subType;
          }
        });
      },
      //选择供应商
      selProvider(row){
        var sel = this.$refs.selProvider;
        sel.show({
          ok:(data)=>{ 
            if(data){ 
              row.providerId = data.providerId;
              row.providerName = data.providerName;
            }
          }
        });
      },
      computedAmount(item){ 
        item.amount = floatObj.multiply(item.price,item.quantity);  
        //var total = 0;
        //this.list.map((mater)=>{
          //total = floatObj.add(total,mater.amount);
       // });
        //this.$emit('on-amount-change',total);
      },

      selEmp(item){
        var sel = this.$refs.selContacts;
        sel.show({
          isMulti:false,
          selectPerson:true,
          selectDept:false,
          ok:()=>{ 
            if(sel.select.length>0){
              item.buyerName = sel.select[0].title;
              item.buyer = sel.select[0].key;
            }
          }
        }); 
      },


    }
  }

</script>

<style type="text/css"> 
  
</style>
