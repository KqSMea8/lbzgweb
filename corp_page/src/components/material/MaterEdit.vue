<template>
  <Modal v-model="show" :title="title" :closable="false" :mask-closable="false">
    <div class="page">
      <Loading :loading="loading">
      <div class="page-form">
        <Form :model="formItem" ref="form" :label-width="100" :rules="ruleValidate" class="form-item">

          <div class="form-sub-title">
            <Icon type="compose"></Icon>
            基本资料
          </div>
          <FormItem label="物料编码" prop="materId">
            <Input v-model="formItem.materId" placeholder="" :disabled="true" />
          </FormItem>  
          <FormItem label="物料名称" prop="materName">
            <Input v-model="formItem.materName" placeholder=""/>
          </FormItem>
          <FormItem label="采购件/产成品">
            <RadioGroup v-model="formItem.type">
                <Radio :label="'1'">采购件</Radio>
                <Radio :label="'2'">产成品</Radio>
            </RadioGroup>
          </FormItem>
          <FormItem v-if="formItem.type=='2'" label="客户" prop="customerId" >
            <Input v-model="formItem.customerId" placeholder="客户编号" :disabled="isEdit"/>
          </FormItem>
          <FormItem v-if="formItem.type=='2'" label="图号" prop="drawing">
            <Input v-model="formItem.drawing" placeholder="" :disabled="isEdit"/>
          </FormItem>

          <FormItem v-if="formItem.type=='1'" label="采购类别" prop="type">
            <Select v-model="formItem.subType" style="width:150px" placeholder="采购类别">
              <Option v-for="item in types" :value="item.argCode" :key="item.argCode">{{ item.argText }}</Option>
            </Select>
          </FormItem>

          <FormItem label="规格" prop="spec" v-if="formItem.type=='1'">
            <Input v-model="formItem.spec" placeholder=""/>
          </FormItem>
          <FormItem label="型号" prop="model" v-if="formItem.type=='1'" >
            <Input v-model="formItem.model" placeholder=""/>
          </FormItem>
          <FormItem label="计量单位" prop="unit">
            <Select v-model="formItem.unit" style="width:150px" placeholder="计量单位">
              <Option v-for="item in unit" :value="item.argCode" :key="item.argCode">{{ item.argText }}</Option>
            </Select>
          </FormItem>
          <FormItem label="财务核算">
            <i-switch v-model="formItem.isAccount"
            :true-value="1" :false-value="0"> 
            </i-switch>
          </FormItem>  
          <FormItem label="管控物料">
            <i-switch v-model="formItem.isControl" 
            :true-value="1" :false-value="0"> 
            </i-switch>
          </FormItem>
          <FormItem label="启用/禁用">
            <i-switch v-model="formItem.status"
            :true-value="1" :false-value="2"> 
            </i-switch>
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

  export default {
    components: {
      Loading, 
    },
    data() {
      return {
        loading: 1,
        show: false,
        //是否编辑 0 添加 1 编辑
        isEdit: false,
        unit: [],
        types:[
         {argCode:'1',argText:'设备配件'},
         {argCode:'2',argText:'常规物料'},
         {argCode:'3',argText:'办公用品'}
        ],
        //表单对象
        formItem: {
          materId: '',
          type:'',
          customerId:'',
          drawing: '',
          materName:'',
          spec:'',
          model:'',
          unit:'', 
          subType:'',
          isAccount:0,
          isControl:0,
          status:1
        },
        oriItem:{ 
        },
        //验证
        ruleValidate: {
          materId: [
            {required: true, message: '编码不能为空', trigger: 'blur'},
          ],
          materName: [
            {required: true, message: '名称不能为空', trigger: 'blur'},
          ],
          unit: [
            {required: true, message: '单位不能为空', trigger: 'blur'},
          ]
        }
      }
    },
    computed: {
      title() {
        if (this.isEdit) {
          return "编辑物料信息";
        } else {
          return "新建物料信息";
        }
      }, 
    },
    mounted(){
      this.loadGroups()
    },
    methods: {
      save() { 
        this.$refs['form'].validate((valid) => {
          if (valid) { 
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
          url = '/api/material/update';
          msg = '修改成功';
        } else {
          url = '/api/material/add';
          msg = '添加成功';
        }

        this.loading = 1;
        this.$http.post(url, this.formItem).then((res) => {
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
      open(mater) {
        this.show = true;
        if(mater.materId != null && mater.materId != '') {
          this.isEdit = true;
          this.get(mater.materId);
        } else {
          this.$http.post('/api/material/getCode?cateCode='+mater.cateCode).then((res) => {
            if (res.data.code === 0 && res.data.data!=null) {
              this.isEdit = false; 
              this.oriItem = Object.assign({
                materId: '',
                type:'1',
                customerId:'',
                drawing: '',
                materName:'', 
                subType:'1',
                spec:'',
                model:'',
                unit:'',
                isAccount:0,
                isControl:0,
                status:1 
              },mater);
              this.oriItem.materId = res.data.data+'[流水号]';
              Object.assign(this.formItem,this.oriItem);
            } else {
              this.loading = 0;
              this.$Message.error(res.data.message)
            }
          }).catch((error) => {
            this.loading = 0;
            this.$Message.error(error.message)
          });


        }
      },
      close() {
        this.show = false;
      },
      reset() { 
        if(this.isEdit){
          $.extend(this.formItem,this.oriItem);
        }else{
          $.extend(this.formItem,{type:'1',subType:'1',isAccount:0,isControl:0},this.oriItem);
        }
      },
      get(materId){
        this.loading = 1;
        this.$http.post("/api/material/get?materId=" + materId, {}).then((res) => {
          this.loading = 0;
          if (res.data.code === 0) { 
            this.oriItem = res.data.data;
            $.extend(this.formItem,this.oriItem);
          } else {
            this.$Message.error(res.data.message);
          }
        }).catch((error) => {
          this.loading = 0;
          this.$Message.error("请求失败，请重新发送")
        });
      },
      loadGroups() {
        this.loading = 1;
        //查询角色列表数据
        this.$http.post("/api/arg/list", {argGroup:'unit'}).then((res) => {
          this.loading = 0;
          if (res.data.code === 0) { 
            this.unit = res.data.data;
          } else {
            this.$Message.error(res.data.message);
          }
        }).catch((error) => {
          this.loading = 0;
          this.$Message.error("请求失败，请重新发送")
        });
      }
    }
  }

</script>

<style type="text/css">
  .form-sub-title {
    font-size: 14px;
    margin: 0 0 12px 18px;
    color: #2b85e4;
  }
</style>
