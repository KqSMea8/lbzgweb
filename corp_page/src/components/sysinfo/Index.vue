<template>
  <div class="page tysysinfo">
    <div class="page-title">系统信息</div>
    <Card class="tysysinfo-card">
      <a>系统配置</a>
      &nbsp;&nbsp;
      <a>系统监控</a>
    </Card>
    <Card class="tysysinfo-card">
      <table class="tysysinfo-table">
        <tr class="tysysinfo-table-title">
          <td colspan="2">服务器</td>
        </tr>
        <tr>
          <td>CPU</td>
          <td><i-progress :percent="25"></i-progress></td>
        </tr>
        <tr>
          <td>内存</td>
          <td><i-progress :percent="80" style="width:200px"></i-progress></td>
        </tr>
      </table>
    </Card>
    <Card class="tysysinfo-card">
      <table class="tysysinfo-table">
        <tr class="tysysinfo-table-title">
          <td colspan="2">系统</td>
        </tr>
        <tr>
          <td>会话用户</td>
          <td>80</td>
        </tr>
      </table>
    </Card>
  </div>
</template>
<script>
import Loading from '@/components/loading';

export default {
  components: {
    Loading
  },
  data() {
    return {
      loading:0,
    }
  },
  mounted:function(){

  },
  computed:{

  },
  methods:{
    load: function() {

    },
  }
}

</script>

<style type="text/css">
  .tysysinfo{

  }
  .tysysinfo-card{
    margin-top: 15px;
  }
  .tysysinfo-table{

  }
  .tysysinfo-table td{
    padding: 4px;
  }
  .tysysinfo-table-title{

  }
</style>
