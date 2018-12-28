<template>
  <Modal v-model="show" :title="title" :closable="false" :mask-closable="false">
    <div class="page customerview">
      <Loading :loading="loading">
      <div class="page-form">
        <Form ref="form" :label-width="100">
          <FormItem label="客户代码">
            {{formItem.customerId}}
          </FormItem>
          <FormItem label="名称" >
            {{formItem.customerName}}
          </FormItem>
          <FormItem label="开户银行" >
            {{formItem.bank}}
          </FormItem>
          <FormItem label="开户账号" >
            {{formItem.bankCardNo}}
          </FormItem>
          <FormItem label="纳税登记号" >
            {{formItem.taxNo}}
          </FormItem>
          <FormItem label="法人代表" >
            {{formItem.legalPerson}}
          </FormItem>
          <FormItem label="联系人" >
            {{formItem.linkMan}}
          </FormItem>
          <FormItem label="联系电话" >
            {{formItem.linkPhone}}
          </FormItem>
          <FormItem label="传真号码" >
            {{formItem.faxNo}}
          </FormItem>
          <FormItem label="邮编" >
            {{formItem.postcode}}
          </FormItem>
          <FormItem label="所属行业" >
            {{formItem.industryName}}
          </FormItem>
          <FormItem label="所属地区" >
            {{formItem.area}}
          </FormItem>
          <FormItem label="地址" >
            {{formItem.address}}
          </FormItem>
          <FormItem label="开发票地址">
            {{formItem.invoiceAddress}}
          </FormItem>
          <FormItem >
            <Button type="primary" icon="checkmark" @click="close">确定</Button>
            <Button type="ghost" @click="edit" style="margin-left: 8px" >编辑</Button>
            <Button type="ghost" @click="close" style="margin-left: 8px">取消</Button>
          </FormItem>
        </Form>
      </div>
      </Loading>
    </div>
    <div slot="footer"></div>
  </Modal>
</template>
<script>
  import Loading from '@/components/loading';
  import areajs from '@/assets/js/area'

  export default {
    components: {
      Loading
    },
    data() {
      return {
        loading: 1,
        show: false,
        //表单对象
        formItem: {
          customerId:'',
          customerName:'',
          bank:'',
          bankCardNo:'',
          taxNo:'',
          legalPerson:'',
          linkMan:'',
          linkPhone:'',
          faxNo:'',
          postcode:'',
          industry:'',
          industryName:'',
          area:'',
          address:'',
          invoiceAddress:'',
        },
        clickEdit:()=>{}
      }
    },
    computed: {
      title() {
        if (this.isEdit) {
          return "编辑客户信息";
        } else {
          return "客户信息";
        }
      },
    },
    mounted(){

    },
    methods: {
      open(item) {
        this.show = true;
        Object.assign(this.formItem,item);
        if(this.formItem.area){
          this.formItem.area = areajs.getText(this.formItem.area).split('|').join('  ');
        }
        this.loading = 0;
      },
      close() {
        this.show = false;
      },
      edit() {
        this.show = false;
        this.clickEdit(); 
      }
    }
  }

</script>

<style type="text/css">
  .ivu-form-item-content {
    word-break:break-all;
  }
</style>
