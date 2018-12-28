<template>
<ListPage ref="page" title="供应商名录" api="/api/providerMaterial/list" 
  :model="this" :beforeLoad="beforeLoad" class="inquirelist">
  <div class="page-tools">
    <table cellpadding="0" cellspacing="0">
      <tr>
        <td>
           <RadioGroup v-model="queryForm.status" type="button" @on-change="query">
            <Radio :label="2">合格供应商物料名录</Radio>
            <Radio :label="1">备选供应商物料名录</Radio><!-- //状态（1.备用 2.合格）, 默认选 合格名录的显示-->
          </RadioGroup>
        </td>
      </tr>
    </table>
  </div>
  <div class="page-searchbox">
    <table cellpadding="0" cellspacing="0">
      <tr> 
         <td>
            <Input v-model="queryForm.providerName" placeholder="供应商"
            readonly="readonly" style="cursor: default;" icon="search" @on-click="selProvider" ></Input>
        </td>
        <td>&nbsp;&nbsp;</td>
        <td>
          <Select v-model="queryForm.materType" style="width:150px" placeholder="物料类别">
            <Option value="">请选择物料类别</Option>
            <Option :value="'A'">A 类</Option>
            <Option :value="'B'">B 类</Option>
            <Option :value="'C'">C 类</Option>
          </Select>
        </td>
        <td> <!-- 调用现成的物料选择控件 @input-item="selectMater"-->
          <SelMaterialInput class="width-2"
            v-model="queryForm.materId"
            :displayText="queryForm.materId"
            :transfer="false">
          </SelMaterialInput>
        </td>
        <td>&nbsp;&nbsp;</td>
        <td>
          <Button @click="query"  type="primary" icon="ios-search">查询</Button>
        </td>
        <td>
          <Button @click="reset" >重置</Button>
        </td>
      </tr>
    </table>
  </div> 
  <ProviderEdit ref="edit" @on-save="query"></ProviderEdit>
  <HistoryPrice ref="price" @on-save="query"></HistoryPrice>
  <SelProvider ref="selProvider"></SelProvider>
</ListPage>
</template>
<script>
  import ListPage from '@/components/page/ListPage'; 
  import DataRowOperateBar from '@/components/commons/DataRowOperateBar'; 
  import ProviderEdit from './ProviderEdit';//编辑
  import HistoryPrice from './HistoryPrice';//编辑
  import SelMaterialInput from '@/components/material/selmaterial/SelMaterialInput';
  import SelProvider from '@/components/provider/SelectProvider';
  import page from '@/assets/js/page';
  export default {
    components: {
      ListPage,
      DataRowOperateBar,
      ProviderEdit,
      HistoryPrice,
      SelMaterialInput,
      SelProvider  //声明新组件
    },
    data() {
      let that = this;
      return {
        columns: [
          /*{
            title: '供应商编码',
            key: 'providerId',
            width: 120,
            fixed: 'left',
          },*/
          {
            title: '供应商名称',
            key: 'providerName',
            width: 140,
            fixed: 'left',
          },
          /*{
            title: '物料代码',
            key: 'materId', 
            width: 120,
            fixed: 'left',
          },*/
          {
            title: '物料名称',
            key: 'materName',
            width: 140,
            align: 'left',
            fixed: 'left',
          }, 
          {
            title: '物料类别',
            key: 'materType',//materTypeName
            width: 100,
            align: 'center'
          },
          {
            title: '规格',
            key: 'spec',
            width: 100,
            align: 'center'
          },
          {
            title: '型号',
            key: 'model',
            width: 100,
            align: 'center'
          },
          {
            title: '产地',
            key: 'origin',
            width: 80,
            align: 'center',
          },
          {
            title: '联系电话',
            key: 'linkPhone',
            width: 120,
            align: 'center',
          },
          {
            title: '联系人',
            key: 'linkMan',
            width: 120,
            align: 'center',
          },
          page.table.initArgColumn({
            title: '采购类别',
            key: 'subType',
            width: 100,
            group: 'material_subtype',
          }), 
          {
            title: '适用工序或产品',
            key: 'use',
            width: 120,
            align: 'center',
          },
          {
            title: '单价（元）',
            key: 'price',
            width: 100,
            align: 'center',
          },
          {
            title: '调价历史',
            key: '查看',
            align: 'center',
            width: 140,
            render:(h,params)=>{
              var row = params.row;
              var text = '查看';
              text = text;
              return h('a',{
                props:{

                },
                on:{
                  click:()=>{
                    this.$refs.price.open({providerId:row.providerId,materId:row.materId});
                  }
                }
              },text);
            }
          },
          {
            title: '备注',
            key: 'remark',
            width: 100,
            align: 'center',
          },
          {
            title: '操作',//操作按钮列表
            width: 80,
            align: 'center',
            render(h, params) {
              let status = params.row.status;
              if (status === 2) {//合格的，需要降级按钮
              return h(DataRowOperateBar, {
                props: {
                  commands: '降级,编辑'
                },
                on: {
                  'on-command': (name) => {
                    that.rowCommand(name, params)
                  }
                }
              });
             }

             if (status === 1) {//备选的不需要降级按钮
              return h(DataRowOperateBar, {
                props: {
                  commands: '编辑'
                },
                on: {
                  'on-command': (name) => {
                    that.rowCommand(name, params)
                  }
                }
              });
             }
            }
          }
        ], 
        queryForm: {
          status:2,//默认选择是合格物料商，备选的为1
          providerId: '',
          providerName: '',
          materId: '',
          materName:'',
          materType: '',
        },
        provider:[],//供应商下拉选择
        material:[],//物料下拉选择
        loading: 0
      }
    },
    mounted() {
      this.reset()
    },
    computed: {},
    methods: {
      query() {
        this.$refs.page.query();
      }, 
      reset() {
        Object.assign(this.queryForm,{
          providerId: '',
          providerName: '',
          materId: '',
          materName:'',
          materType: '',
        });
        this.query();
      },  
      beforeLoad(){

      }, 
      switStorage(){
        this.reset();
      }, 
      rowCommand(name, params) {
        if (name === '编辑') {
          if(!this.$user.hasPower('cggl.gysml.edit')){
            this.$Message.error('暂无权限！');
            return;
          }
          this.update(params.row);//只能编辑物料类别
          return;
        }

        if (name === '降级') {
          if(!this.$user.hasPower('cggl.gysml.demotion')){
            this.$Message.error('暂无权限！');
            return;
          }

          this.$Modal.confirm({
            title: '降级确认',
            content: '<p>确定降级已选择的记录吗？</p>',
            onOk: () => {
              var providerId = params.row.providerId;
              var materId = params.row.materId;
              this.loading = 1;
              this.$http.post('/api/providerMaterial/degrade', {"providerId":providerId,"materId":materId}).then((res) => {
              this.loading = 0;
              if (res.data.code === 0) {
                  this.$Message.success("降级成功");
                  this.query();//重新更新列表界面
                } else {
                  this.$Message.error(res.data.message)
                }
              }).catch((error) => {
                this.loading = 0;
                this.$Message.error(error.toString())
              });
            }
          });
        }
      },  
      selProvider(){
         var sel = this.$refs.selProvider;
         sel.show({
          ok:(data)=>{
            if(data){
              this.queryForm.providerId = data.providerId;
              this.queryForm.providerName = data.providerName;
            }
          }
        });
      },

      update(item) {
        this.$refs.edit.open(item);
      }
    }
  }
</script>

<style type="text/css">
  .inquirelist .status-2{
    color:#ff6600;
  } 
</style>
