<template>
  <Modal v-model="show" :title="title" :closable="false" :mask-closable="false">
    <div class="page">
        <Loading :loading="loading" :mask="mask"> 
        <div class="page-form">
        <Form :model="formItem" ref="empForm" :label-width="80" :rules="ruleValidate" class="form-item">
          <div class="form-sub-title">
            <Icon type="compose"></Icon>基本资料
          </div>
          <FormItem label="姓名" prop="trueName">
            <Input v-model="formItem.trueName" placeholder="不超过30个字符"  :disabled="this.installed==0"/>
          </FormItem>
          <FormItem label="账号" prop="empId">
            <Input v-model="formItem.empId" placeholder="成员唯一标识,添加成功后不可更改"  :disabled="isEdit==1"/>
          </FormItem>
          <FormItem label="手机号码" prop="mobile">
            <Input v-model="formItem.mobile"  placeholder="手机号不能为空"/>
          </FormItem>
          <FormItem label="性别">
            <Radio-group v-model="formItem.sex" >
              <Radio label="1">男</Radio>
              <Radio label="2">女</Radio>
            </Radio-group>
          </FormItem>
          <FormItem label="电子邮箱">
            <Input v-model="formItem.email" />
          </FormItem>
          <FormItem label="微信号">
            <Input v-model="formItem.weixin" />
          </FormItem>
          <FormItem label="部门" prop="deptName"> 
            <i-input v-model="formItem.deptName" placeholder="请选择部门" readonly> 
              <Button slot="append" @click="selectDept" icon="more"></Button>
            </i-input>
            
          </FormItem>
          <FormItem label="职位">
            <Input v-model="formItem.duty"  :disabled="this.installed==0"/>
          </FormItem>

          <div class="form-sub-title">
             <Icon type="compose"></Icon>详细资料
          </div>

          <FormItem label="员工生日">
            <Date-picker type="date" placeholder="选择日期"  v-model="formItem.birthSolar" 
                         @on-change="getBirth"></Date-picker>
          </FormItem>
          <FormItem label="入职日期">
            <Date-picker type="date" placeholder="选择日期" 
              v-model="formItem.joinDate" format="yyyy-MM-dd"
              @on-change="getJoinDate"></Date-picker>
          </FormItem>
          <FormItem label="生日提醒">
            <Radio-group v-model="formItem.birthReminder" >
              <Radio label="0">阳历</Radio>
              <Radio label="1">阴历</Radio>
            </Radio-group>
          </FormItem>
          <FormItem>
            <div class="">
              <Button icon="checkmark"  type="primary" @click="save">保存</Button>
              <Button  type="ghost" @click="reset" style="margin-left: 8px" v-if="this.isEdit==1">重置</Button>
              <Button  type="ghost" @click="close" style="margin-left: 8px">取消</Button>
            </div>
          </FormItem>
        </Form>
        </div>
        </Loading>
      </div>
      <div slot="footer"></div>
      <SelectDept ref="dept" @on-check="updateDept" @on-close="closeDept"></SelectDept>
  </Modal>
</template>
<script>
import SelectDept from '@/components/commons/SelectDept';
import Loading from '@/components/loading';

export default {
  components: {
    SelectDept,
    Loading
  },
  data() {
    return {
      installed:1,
      loading:1,
      mask:2,
      show:false,
      //是否编辑 0 添加 1 编辑
      isEdit:0,
      //表单对象
      formItem: {
        trueName: '',
        empId: '',
        sex: 1,
        mobile: '',
        email:'',
        weixin: '',
        deptId: '',
        deptName: '',
        duty: '',
        birthSolar: '',
        joinDate: '',
        birthReminder: 0
      },
      ruleValidate: {
        trueName: [
          {required:true,whitespace: true,message: '姓名不能为空', trigger: 'blur'},
          {type: 'string', max: 30, message: '不能超过30个字', trigger: 'blur' }
        ],
        empId: [
          {required:true,whitespace: true,type: 'string', message: '成员唯一标识,不可更改', trigger: 'blur'}
        ],
        mobile: [
          {required:true, message: '手机号不能为空', trigger: 'blur'},
          {pattern: /^1[3|4|5|7|8][0-9]\d{8}$/, message: '手机号格式错误', trigger: 'blur' }
        ],
        deptName: [
          {required:true,whitespace: true,message: '部门不能为空',trigger: 'blur'}
        ]
      }
    }
  },
  computed:{
    title(){
      if(this.isEdit===0){
        return "添加员工";
      }else{
        return "修改成员详情";
      }
    } 
  },
  methods:{
    save(){
      this.$refs['empForm'].validate((valid) => {
        if (valid) {
            this.loading = 1;
            this.mask = 1;
            this.saveEmp();
          } else {
            return;
          }
      });
    },
    getBirth(val){
      this.formItem.birthSolar = val;
    },
    getJoinDate(val){
      this.formItem.joinDate = val;
    },
    saveEmp(){
        let url = '';
        let msg = '';
        if(this.isEdit == 0){
           url='/api/contacts/emp/add';
           msg = '添加成功';
        }else{
           url = '/api/contacts/emp/update';
           msg = '修改成功';
        }
        this.$http.post(url, this.formItem).then((res) => {
          if (res.data.code === 0) {
            this.$Message.success(msg);
            this.close();
            this.$emit("on-load");
          } else {
            this.loading = 0;
            this.$Message.error(res.data.message)
          }
        }).catch((error) => {
          this.loading = 0;
          this.$Message.error(error.message)
        });
    },
    open(empId) { 
      this.show = true;
      this.loading = 1;
      this.$refs['empForm'].resetFields();
      this.clear();
      if(empId != '' && empId != null){
        this.isEdit = 1;
        this.getEmp(empId);
      }else{
        this.isEdit = 0;
        this.loading = 0;
        this.mask = 2;
      }
    },
    getEmp(empId){
        this.$http.post('/api/contacts/emp/get?empId=' + empId, {}).then((res) => {
          if (res.data.code === 0) {
            this.loading = 0;
            this.formItem = res.data.data;
          } else {
            this.loading = 0;
            this.$Message.error(res.data.message)
          }
        }).catch((error) => {
          this.loading = 0;
          this.$Message.error(error.message)
        });
    },
    closeDept(){
      this.show = true;
    },
    close(){
      this.show = false;
    },
    reset(){
       this.getEmp(this.formItem.empId);
    },
    clear(){
      this.$refs.dept.setCheck([]);
      this.formItem = {
        trueName: '',
        empId: '',
        sex: 1,
        mobile: '',
        email:'',
        weixin: '',
        deptId: '1',
        deptName: '',
        duty: '',
        birthSolar: '',
        joinDate: '',
        birthReminder: 0
      };
    },
    selectDept(){
      this.show = false;
      this.$refs.dept.open();
      let deptIds = (this.formItem.deptId || '').split(',');
      this.$refs.dept.setCheck(deptIds);
    },
    updateDept(depts){
      this.show = true;
      this.formItem.deptId = depts.map((item) => {return item.deptId}).join(',');
      this.formItem.deptName = depts.map((item) => {return item.deptName}).join(',');
      this.$refs.dept.close();
    }
  }
}

</script>

<style type="text/css">
  .form-sub-title {
      font-size:14px;
      margin: 0px 0px 12px 18px;
      color: #20bfee;
  }
  .form-sub-title .ivu-icon{
     margin-right: 5px;
  }
</style>
