<template>
  <div class="page">
    <h3>小组件</h3>
    <h4>1. 相关人</h4>
    <RelatedPerson :persons="persons"></RelatedPerson>
    <h4>2. 评论</h4> 
    <h4>3. 选择部门</h4>
    <Button @click="showDept">选择部门</Button> <Button @click="setSelectDept">设置部门选中(1,2,3)</Button>
    <SelectDept ref="selectDept"></SelectDept>
    <h4>4. 页头</h4>
    <div class="page">
      <div class="page-bar"> 
        <div class="page-title">
          布局组件
        </div>
      </div> 
    </div>
    <div style="height:10px;"></div>
    <div class="page">
      <div class="page-bar">
        <LayoutHor>
          <div slot="left" style="width:200px;">
            <Icon type="document"></Icon> 应用中心
          </div>
          <div slot="right">
            <Button size="small" icon="refresh" type="warning">刷新应用状态</Button>
          </div>
        </LayoutHor>
      </div> 
    </div>
    <div style="height:10px;"></div>
    <div class="page">
      <div class="page-bar">
        <LayoutHor>
          <div slot="left">
            <Button size="small" icon="chevron-left"  type="warning">返回</Button>
          </div>
          <div class="page-bar-title">工作日志 - 设置</div>
        </LayoutHor>
      </div> 
    </div>
    <div style="height:20px;"></div>
    <h4>5. 附件</h4>
    <Attachment :queryCondition="attachCondition"></Attachment>
    <div style="height:20px;"></div>
    <h4>6. 选人组件</h4>
    <SelectEmp :persons="selectedPersons" @on-selected="selectEmp"></SelectEmp>
    <Button @click="getSelectEmp">获取选中人员</Button>
    <h4>7. Loading</h4>
    <Loading title="加载中..." :loading="1">
      <p>内容内容内容内容内容内容内容</p>
      <p>内容内容内容内容内容内容内容</p>
      <p>内容内容内容内容内容内容内容</p>
      <p>内容内容内容内容内容内容内容</p>
      <p>内容内容内容内容内容内容内容</p>
      <p>内容内容内容内容内容内容内容</p>
      <p>内容内容内容内容内容内容内容</p>
      <p>内容内容内容内容内容内容内容</p>
      <Button type="primary">确定</Button>
      <Button>取消</Button>
    </Loading>
    <br/>
    <Loading title="加载中..." :loading="1" :mask="2">
      <p>内容内容内容内容内容内容内容</p>
      <p>内容内容内容内容内容内容内容</p>
      <p>内容内容内容内容内容内容内容</p>
      <p>内容内容内容内容内容内容内容</p>
      <p>内容内容内容内容内容内容内容</p>
      <p>内容内容内容内容内容内容内容</p>
      <p>内容内容内容内容内容内容内容</p>
      <p>内容内容内容内容内容内容内容</p>
      <Button type="primary">确定</Button>
      <Button>取消</Button>
    </Loading>
    <br/>
    <Loading title="加载出错！" :loading="2">
      <p>内容内容内容内容内容内容内容</p>
      <Button type="primary">确定</Button>
      <Button>取消</Button>
    </Loading>
    <br/>
    <Loading title="ok" :loading="0">
      <p>内容内容内容内容内容内容内容</p>
      <Button type="primary">确定</Button>
      <Button>取消</Button>
    </Loading>
  </div>
</template>
<script>

  import RelatedPerson from '@/components/commons/RelatedPerson'; 
  import SelectDept from '@/components/commons/SelectDept';
  import LayoutHor from '@/components/layout/LayoutHor';
  import Attachment from '@/components/commons/Attachment';
  import SelectEmp from '@/components/commons/SelectEmp';
  import Loading from '@/components/loading';

  export default {
    data() {
      return {
        persons: 'rx',
        selectedPersons: '',
        isRange:true,
        replyCondition: null,
        attachCondition: null
      }
    },
    components: {
      RelatedPerson, 
      SelectDept,
      LayoutHor,
      Attachment,
      SelectEmp,
      Loading
    },
    mounted: function () {
      this.loadPerson();
      this.load();
      this.loadAttach();

      this.selectedPersons = 'renxin,chengxinjing,e001,liuyun';
    },
    methods: {
      loadPerson: function () {
        var that = this;
        setTimeout(function () {
          that.persons = 'renxin,chengxinjing,liqian,zhangsan,renxin,chengxinjing,zhangpengfei,zhangsan,renxin,chengxinjing,zhangpengfei,zhangsan,renxin,chengxinjing,zhangpengfei,zhangsan,renxin,chengxinjing,zhangpengfei,zhangsan,renxin,chengxinjing,zhangpengfei,zhangsan,renxin,chengxinjing,zhangpengfei,zhangsan,renxin,chengxinjing,zhangpengfei,zhangsan';
        }, 500);
      },
      load: function () {
        var that = this;
        setTimeout(function () {
          that.replyCondition = {
            catelog: 'worklog',
            dataId: 1,
          };
        }, 500);
      },
      showDept:function() {
         this.$refs.selectDept.setCheck([]);
         this.$refs.selectDept.open();
      },
      setSelectDept:function() {
         this.$refs.selectDept.setCheck([1,2,3]);
         this.$refs.selectDept.open();
      },
      loadAttach:function(){
        var that = this;
        setTimeout(function () {
          that.attachCondition = {
            module: 'worklog',
            objId: 155
          };
        }, 500);
      },
      getSelectEmp:function(){
        alert(this.selectedPersons);
      },
      selectEmp:function(emps){
        this.selectedPersons = emps;
      }
    }
  }
</script>
<style>
  .page h4 {
    padding: 6px 0px;
  }
</style>
