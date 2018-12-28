<template>
  <Modal v-model="show" :title="title" :closable="false" :mask-closable="false" class="stockedit">
    <div class="page">
      <Loading :loading="loading">
      <div class="page-form">
        <Form :model="formItem" ref="form" :label-width="100" :rules="ruleValidate" class="form-item">
          <FormItem label="物料编码" prop="materId">
            <SelMaterialInput class="width-2" 
              v-model="formItem.materId"
              @input-item="selectMater"
              :displayText="formItem.materId"
              :disabled="isEdit"
              :transfer="false"></SelMaterialInput>
          </FormItem>
          <FormItem label="物料名称" prop="materName">
            {{formItem.materName}}
          </FormItem>
          <FormItem label="规格" prop="spec">
            {{formItem.spec}}
          </FormItem>
          <FormItem label="型号" prop="model">
            {{formItem.model}}
          </FormItem>
          <FormItem label="批次" prop="batch">
            <Input v-model="formItem.batch" :disabled="isEdit" placeholder="" class="width-2"/>
          </FormItem>
          <FormItem label="货位编码" prop="placeId">
            <SelPlaceInput ref="selplace"  class="width-1"
              v-model="formItem.placeId"
              @input-item="selectPlace"
              :storageId="formItem.storageId"
              :displayText="formItem.placeId"
              :disabled="isEdit"
              :transfer="false"
            ></SelPlaceInput>
          </FormItem>
          <FormItem label="货位名称" prop="placeName">
            {{formItem.placeName}}
          </FormItem>
          <FormItem label="数量" prop="quantity"> 
            <InputNumber :max="9999999999" :min="0" v-model="formItem.quantity" class="width-1" @on-change="computedAmoun"></InputNumber>
          </FormItem>
          <FormItem label="单位" prop="unitName">
            {{formItem.unitName}}
          </FormItem>
          <FormItem label="单价" prop="price">
            <InputNumber :max="9999999999" :min="0" v-model="formItem.price" class="width-1" @on-change="computedAmoun"></InputNumber>
          </FormItem>
          <FormItem label="金额" prop="amount">
            <InputNumber :max="9999999999" :min="0" v-model="formItem.amount" class="width-1"></InputNumber>
          </FormItem>
          <FormItem>
            <Button type="primary" icon="checkmark" @click="save">保存</Button>
            <Button type="ghost" @click="reset" style="margin-left: 8px" >重置</Button>
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
  import SelPlaceInput from '@/components/storage/selplace/SelPlaceInput';
  import floatObj from '@/assets/js/floatObj';
  import SelMaterialInput from '@/components/material/selmaterial/SelMaterialInput';

  export default {
    components: {
      Loading, SelPlaceInput,SelMaterialInput
    },
    data() {
      return {
        loading: 1,
        show: false,
        //是否编辑 0 添加 1 编辑
        isEdit: false,
        industry: [],
        //表单对象
        formItem: {
          storageId:'',
          materId:'',
          materName:'',
          spec:'',
          model:'',
          batch:'',
          unitName:'',
          unit:'',
          price:0,
          quantity:0,
          amount:0,
          placeId:'',
          placeName:''
        },
        oriItem:{
        },
        //验证
        ruleValidate: {
          materId: [
            {required: true, whitespace: true, message: '请选择物料', trigger: 'blur'}
          ],
          batch: [
            {required: true, whitespace: true, message: '批号不能为空', trigger: 'blur'},
            {type: 'string', max: 50, message: '不能超过50个字符', trigger: 'blur'}
          ],
          placeId: [
            {required: true, whitespace: true, message: '请选择货位', trigger: 'blur'}
          ],
        }
      }
    },
    computed: {
      title() {
        if (this.isEdit) {
          return "库存记录";
        } else {
          return "库存记录";
        }
      },
    },
    mounted(){
    },
    methods: {
      save() {
        this.$refs['form'].validate((valid) => {
          if (valid) {
            if(this.formItem.placeId==''){
              this.$Message.error('请选择货位');
              return;
            }
            this.saveItem();
          } else {
            return;
          }
        });
      },
      saveItem() {
        let url = '';
        let msg = '';
        if (this.isEdit) {
          url = '/api/storage/stock/batch';
          msg = '修改成功';
        } else {
          url = '/api/storage/stock/batch';
          msg = '添加成功';
        }

        this.loading = 1;
        this.$http.post(url, [this.formItem]).then((res) => {
          this.loading = 0;
          if (res.data.code === 0) {
            this.$Message.success(msg);
            this.close();
            this.$emit("on-save");
          } else {
            this.$Message.error(res.data.message)
          }
        }).catch((error) => {
          this.loading = 0;
          this.$Message.error(error.message)
        });
      },
      selectMater(mater){
        if(mater != null){
          this.formItem.materId = mater.materId;
          this.formItem.materName = mater.materName;
          this.formItem.unit = mater.unit;
          this.formItem.unitName = mater.unitName;
          this.formItem.spec = mater.spec;
          this.formItem.model = mater.model;
        }else{
          this.formItem.materId = '';
          this.formItem.materName = '';
          this.formItem.unit = '';
          this.formItem.unitName = '';
          this.formItem.spec = '';
          this.formItem.model = '';
        }
        this.$refs['form'].validateField('materId');
      },
      selectPlace(place){
        if(place != null){
          this.formItem.placeName = place.placeName; 
        }else{this.formItem.placeName = '';
        }
        this.$refs['form'].validateField('placeId');
      },
      open(item) {
        this.show = true;
        this.$refs['form'].resetFields();
        // 编辑时，需要给对象赋一个 id 字段
        if (item.id) {
          this.isEdit = true;
          this.oriItem = item;
          this.loading = 0;
        } else {
          this.isEdit = false;
          this.oriItem = Object.assign({
            storageId:'',
            materId:'',
            materName:'',
            spec:'',
            model:'',
            batch:'',
            unitName:'',
            unit:'',
            price:0,
            quantity:0,
            amount:0,
            placeId:'',
            placeName:''},item);
          this.loading = 0;
        }
        Object.assign(this.formItem,this.oriItem);
      },
      close() {
        this.show = false;
      },
      reset() {
        $.extend(this.formItem,this.oriItem);
      },
      computedAmoun(){
        var val = 0;
        val = floatObj.multiply(this.formItem.quantity,this.formItem.price);
        this.formItem.amount = val;
      }
    }
  }

</script>

<style type="text/css"> 
  .stockedit .width-1{
    width: 160px;
  }
  .stockedit .width-2{
    width: 280px;
  }
  .stockedit .page{
    padding: 0px;
  }

  .stockedit .page-searchbox{
    margin-top: 0px;
  }
</style>
