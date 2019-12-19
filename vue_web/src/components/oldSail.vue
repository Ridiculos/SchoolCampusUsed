<template>
  <div class="oldSailDiv">
    <!--      首页条-->
    <index-header></index-header>

    <!--    网页主体-->
    <el-row style="width: 80%;margin: 0 auto;padding-top: 80px;margin-bottom: 40px;">
      <el-col>
        <h2>闲置卖钱：</h2>
        <el-card shadow="always">
          <el-form label-position="top" label-width="80px" :model="submitGoodsForm" :rules="rules"
                   ref="submitGoodsForm">
            <el-col :span="18" :offset="2">
              <el-form-item label="商品图" enctype="multipart/form-data">
                <!--图片上传组件-->
                <el-upload
                  list-type="picture-card"
                  ref="upload"
                  :action="action"
                  :on-success="submitSuccess"
                  :on-error="submitError"
                  :auto-upload="false"
                  :limit="4"
                  multiple
                  :on-exceed="submitIndexOut"
                  accept="image/*">
                  <i slot="default" class="el-icon-plus"></i>
                  <div slot="file" slot-scope="{file}">
                    <img
                      class="el-upload-list__item-thumbnail"
                      :src="file.url" alt="请检查网络连接"
                    >
                    <span class="el-upload-list__item-actions">
        <span
          class="el-upload-list__item-preview"
          @click="handlePictureCardPreview(file)"
        >
          <i class="el-icon-zoom-in"></i>
        </span>
      </span>
                  </div>
                </el-upload>
                <el-dialog :visible.sync="dialogVisible">
                  <img width="100%" :src="dialogImageUrl" alt="">
                </el-dialog>
                <el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload">上传到服务器
                </el-button>
<!--                <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>-->
              </el-form-item>
            </el-col>
            <el-col :span="9" :offset="2" style="margin-top: 50px;">
              <el-form-item label="商品名称" prop="name">
                <el-input v-model="submitGoodsForm.name" maxlength="25" show-word-limit></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="9" :offset="2" style="margin-top: 50px;">
              <el-form-item label="商品价格" prop="price">
                <el-input v-model="submitGoodsForm.price"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="9" :offset="2" style="margin-top: 20px;">
              <el-form-item label="商品新旧程度 %" prop="goodsLevel">
                <el-input v-model="submitGoodsForm.goodsLevel"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="9" :offset="2" style="margin-top: 20px;">
              <el-form-item label="商品介绍" prop="dscrip">
                <el-input
                  type="textarea"
                  :autosize="{ minRows: 2, maxRows: 8}"
                  placeholder="请输入内容"
                  v-model="submitGoodsForm.dscrip"
                  maxlength="100"
                  show-word-limit>
                </el-input>
              </el-form-item>
            </el-col>
          </el-form>
        </el-card>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="5" :offset="9">
        <el-button type="primary" @click="submitForm('submitGoodsForm')" style="width: 100%;">保存</el-button>
      </el-col>
    </el-row>

    <!--      弹出提示框-->
    <el-dialog
      title="提示"
      :visible.sync="centerDialogVisible"
      width="30%"
      center>
      <div style="text-align: center">
        <h4>{{ dialogValue }}</h4>
        <span slot="footer" class="dialog-footer">
            <el-button type="primary" @click="clickButton" style="margin-top: 30px;">确 定</el-button>
          </span>
      </div>
    </el-dialog>

    <!--      首页尾-->
    <index-footer></index-footer>
  </div>
</template>

<script>
  import IndexHeader from './pages/index-header'
  import IndexFooter from './pages/index-footer'

  export default {
    name: "oldSail",
    data() {
      let checkPrice = (rule, value, callback) => {
        let numberRex = /^([1-9]\d*(\.\d*[1-9])?)|(0\.\d*[1-9])$/;
        if (value === "") {
          return callback(new Error('商品价格不能为空'));
        } else {
          if (!numberRex.test(value)) {
            callback(new Error('请输入正数'));
          } else {
            if (value < 0) {
              callback(new Error('必须大于 0'));
            } else {
              callback();
            }
          }
        }
      };
      let checkGoodsLevel = (rule, value, callback) => {
        let numberRex = /^[1-9]\d*$/;
        if (value === "") {
          return callback(new Error('商品新旧程度不能为空'));
        } else {
          if (!numberRex.test(value)) {
            callback(new Error('请输入正整数'));
          } else {
            if (value < 1 || value > 99) {
              callback(new Error('必须在 1 - 99 之间'));
            } else {
              callback();
            }
          }
        }
      };
      let checkString = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('请填写内容'));
        } else {
          callback();
        }
      };
      return {
        submitGoodsForm: {
          name: "",
          price: "",
          goodsLevel: "",
          dscrip: "",
          userId: "",
          goodsId:"",
        },
        dialogImageUrl: '',
        dialogVisible: false,
        disabled: false,
        dialogValue: '',
        centerDialogVisible: false,
        submitFlag:false,
        action:"",
        rules: {
          name: [
            {validator: checkString, trigger: 'blur'}
          ],
          price: [
            {validator: checkPrice, trigger: 'blur'}
          ],
          goodsLevel: [
            {validator: checkGoodsLevel, trigger: 'blur'}
          ],
          dscrip: [
            {validator: checkString, trigger: 'blur'}
          ]
        }
      }
    },
    components: {
      IndexHeader, IndexFooter
    },
    mounted() {
      //先从服务端获取一个随机不重复的商品ID作为准备新上传的闲置商品ID
      let self = this;
      $.get("http://localhost:8083/goods/getRandomGoodsId.do",function (data) {
        self.submitGoodsForm.goodsId = data.goodsId;
        //设置上传图片路径，需要一个商品ID
        self.action = "http://localhost:8083/upload/uploadFile.do?goodsId=" + data.goodsId;
      },"json");
    },
    methods: {
      submitForm(formName) {
        if(this.submitFlag){
          this.$refs[formName].validate((valid) => {
            if (valid) {
              // alert('submit!');
              let self = this;
              this.submitGoodsForm.userId = window.sessionStorage.getItem("userId");
              let jsonMsg = JSON.stringify(this.submitGoodsForm);

              $.post("http://localhost:8083/goods/createGoods.do", jsonMsg, function (data) {
                if (data.code === 1) {
                  self.dialogValue = "保存成功";
                } else {
                  self.dialogValue = "保存失败，错误码：" + data.code;
                }
              }, "json");
               self.centerDialogVisible = true;
            } else {
              // console.log('error submit!!');
              return false;
            }
          });
          this.submitFlag = false;
        }else{
          this.dialogValue = "请先上传商品图";
          this.centerDialogVisible = true;
        }
      },
      clickButton() {
        this.centerDialogVisible = false;
        let self = this;
        if(this.dialogValue === "保存成功"){
          setTimeout(function () {
            self.$router.push("/person#personOldId");
          },1000);
        }
      },
      handlePictureCardPreview(file) {
        this.dialogImageUrl = file.url;
        this.dialogVisible = true;
      },
      submitUpload() {
        this.$refs.upload.submit();
      },
      submitSuccess() {
        // console.log("成功")
        this.dialogValue = "已上传";
        this.centerDialogVisible = true;
        this.submitFlag = true;
      },
      submitError() {
        // console.log("失败")
        this.dialogValue = "图片上传失败，请检查网络连接";
        this.centerDialogVisible = true;
        this.submitFlag = false;
      },
      submitIndexOut(){
        this.dialogValue = "图片数量超限";
        this.centerDialogVisible = true;
      }
    }
  }
</script>

<style scoped>
  h2 {
    font-weight: 400;
    color: #1f2f3d;
    font-size: 28px;
  }
</style>
