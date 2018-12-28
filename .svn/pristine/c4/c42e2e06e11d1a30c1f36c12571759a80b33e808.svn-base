<template>
  <div class="page page-bill"> 
    <div class="page-bar">
      <LayoutHor>
        <div slot="left">
          <Button size="small" @click="goBack" icon="chevron-left"  type="warning">返回</Button>
        </div>
        <div class="page-bar-title">询价单 - 查看</div>
      </LayoutHor>
    </div>
    <Loading :loading="loading">
    <div class="baseinfo">
      <div class="subheader">
        单据表头
      </div>
      <Row>
        <Col span="14">
          <div class="">
            <table cellspacing="0" cellpadding="0">
              <tr>
                <td class="label">询价单编号</td>
                <td>{{formItem.inquireId}}</td>
                <td class="label">录入日期</td>
                <td>
                  {{formItem.inDate}}
                </td>
              </tr>
              <tr>
                <td class="label">客户</td>
                <td>
                  {{formItem.customerName}}
                </td>
                <td class="label">业务员</td>
                <td>{{formItem.salesName}}</td>
              </tr>
              <tr>
                <td class="label">备注</td>
                <td colspan="3">{{formItem.remark}}</td> 
              </tr>
            </table>
          </div> 
        </Col>
        <Col span="10">
          <table>
            <tr>
              <td>
                <Tag v-if="formItem.isInquire==1" color="green">需求询价</Tag>
                <Tag v-else>需求询价</Tag>
                &nbsp;
                <Tag v-if="formItem.hasProcedure==1" color="green">含加工工序</Tag>
                <Tag v-else>含加工工序</Tag> 
                &nbsp;
                <Tag  v-if="formItem.isForeign==1" color="green">是否外销</Tag>
                <Tag v-else>是否外销</Tag> 
                &nbsp;
                <Tag v-if="formItem.isCraftDesign==1" color="green">进行工艺设计</Tag>
                <Tag v-else>进行工艺设计</Tag>
            </td>
          </tr> 
          <tr>
              <td >
                <UploadBox v-model="formItem.files" :readonly="true"
                  style="height: 90px;"></UploadBox>
              </td>
            </tr> 
          </table>
        </Col>
      </Row>  
    </div> 
    <div class="subheader">
      单据明细
    </div>
    <Editable v-for="(item,index) in formItem.detailList"
        :title="'物料 ' + (index+1)" :material="item"></Editable>
    <table class="billfooter" cellpadding="0" cellspacing="0">
      <tr>
        <td class="label">工艺员</td>
        <td width="100">{{formItem.designerName}}</td>
        <td class="label">预算日期</td>
        <td width="100">{{formItem.completeTime}}</td>
      </tr> 
    </table>  
    <table class="savebar" cellpadding="0" cellspacing="0">
      <tr> 
        <td class="save" @click="ok">
          确定
        </td>
        <td class="reset" @click="reset">
          重置
        </td>
        <td></td>
      </tr>
    </table> 
    </Loading> 
  </div> 
</template>
<script> 
  import Loading from '@/components/loading'; 
  import LayoutHor from '@/components/layout/LayoutHor';
  import Editable from './InquireDetailCard'; 
  import dateFormat from '@/assets/js/dateFormat';
  import UploadBox from '@/components/upload/Index';

  export default {
    components: { 
      Loading, 
      LayoutHor,
      Editable, 
      UploadBox
    },
    data() { 
      return { 
        loading: 0,
        inquireId:'',
        formItem:{ 
          inquireId:'',
          customerId:'',
          customerName:'',
          inDate:'',
          isInquire:0,
          hasProcedure:0,
          isForeign:0,
          isCraftDesign:0,
          sales:'',
          salesName:'',
          remark:'',
          designer:'',
          completeTime:'',
          files:'',
        },
        list:[],
        unitArgs:[],
        oriItem:{},
        isNew:true,
      }
    },
    mounted: function () {  
      this.inquireId = this.$route.query.id;
      this.load();
    },
    computed: {},
    methods: { 
      load() {
        this.loading = 1;
        this.$http.post("/api/inquire/get?inquireId=" + this.inquireId,{}).then((res) => {
          this.loading = 0;
          if (res.data.code == 0) { 
            if(res.data.data){
              Object.assign(this.formItem,res.data.data);
            }else{
              this.$Message.error('询价单不存在！');
              this.goBack();
            }
          } else {
            this.$Message.error(res.data.message);
          }
        }).catch((error) => {
          this.loading = 0;
          this.$Message.error("操作失败！")
        });
      },
      ok(){
        this.goBack();
      },
      reset(){
        this.load();
      }, 
      goBack(){
        this.$router.go(-1);
      }
    }
  }

</script>

