  <template>
<ul>
  <li>
      <div v-bind:class="{'tv-node':1,'select':model.select}" :style="style" @click="model.doEvent('select',$event)">

          <table class="tv-node-row">
            <tr>
                <td class="tv-node-row-arrow">
                    <span v-bind:class="{'tv-arrow':1,'open':model.open,'close':!model.open,'empty':model.children.length==0}" @click="model.doEvent('fold',$event)">
                    <Icon type="arrow-right-b" class="arrow-close"></Icon>
                    <Icon type="arrow-down-b" class="arrow-open"></Icon>
                    </span>
                </td>
                <td class="tv-node-row-check">
                    <span @click="$event.cancelBubble=true">
                        <span v-if="!model.check" @click="model.check=true" class="tv-checkbox">
                            <Icon type="android-checkbox-outline-blank"></Icon>
                        </span>
                        <span v-else class="tv-checkbox tv-checkbox-check" @click="model.check=false">
                            <Icon type="android-checkbox"></Icon>
                        </span>
                    </span>
                </td>
                <td>
                    {{model.title}}
                </td>
                <td></td>
            </tr>
        </table>
      </div>
      <ul v-bind:class="{'tv-hidden':!model.open}">
          <li v-for="node in model.children">
              <DeptNodeCheck :model="node"></DeptNodeCheck>
          </li>
      </ul>
  </li>
</ul>
</template>

<script>

export default {
  name:'DeptNodeCheck',
  components: {
  },
  props: {
    model: {
      type:[Object],
      default:{}
    }
  },
  data() {
    return {};
  },
  mounted(){
  },
  computed:{
    style() {
      if(this.model && this.model.level){
        return 'padding-left:' + this.model.level*14 + 'px';
      }
      return '';
    }
  }
}
</script>

<style>
  .tv{padding: 10px;border:1px solid #dedede;}/*组件*/
  .tv-node{padding: 2px 0px;cursor: default;}
  .tv-node:hover{background-color: #eaf2ff}
  .tv-node.select{background-color: #C4DDF9;}

  .tv-node-row{width: 100%;line-height: 16px;}
  .tv-node-row td{margin: 0px;padding: 0px;vertical-align: middle;}
  .tv-node-row-arrow{width: 14px;}
  .tv-node-row-check{width: 16px;text-align: center;}

  .tv-children{}
  .tv-children li{padding: 2px;}
  .tv-title{}

  .tv-arrow{cursor: pointer;background-color:transparent;width: 14px;display: inline-block;text-align: center;}
  .tv-arrow:hover{color:#39f}
  .tv-arrow.open .arrow-close,
  .tv-arrow.open .arrow-empty {display: none}
  .tv-arrow.close .arrow-open,
  .tv-arrow.close .arrow-empty {display: none}
  .tv-arrow.empty .arrow-open,
  .tv-arrow.empty .arrow-close{display: none}

  .tv-checkbox{font-size: 14px;cursor: pointer;}
  .tv-checkbox.tv-checkbox-check{color:#39f;font-weight: bold;}

  .tv-hidden{display: none;}
  .tv-node:hover .tv-toolbar{display: inline-block;}
  .tv-toolbar .ivu-btn{padding: 0px;}

  /*combotree*/
  .combotree .tv{padding: 0px;margin:0px;border:0px;max-height: 240px;overflow-y:auto;min-width: 240px;overflow-x:hidden; }
  .combotree-input{width: 200px;text-align: left;}
</style>

