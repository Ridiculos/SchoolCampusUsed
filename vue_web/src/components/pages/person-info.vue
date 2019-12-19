<template>
    <div class="personInfo" id="personInfoId">
      <h2>个人信息：</h2>
      <el-card shadow="always">
        <el-form label-position="top" label-width="80px" :model="formLabelAlign" :rules="rules" ref="formLabelAlign">
          <el-row :gutter="100">
            <el-col :span="9" :offset="2">
              <el-form-item label="用户名">
                <el-input v-model="formLabelAlign.username" disabled></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="9" :offset="1">
              <el-form-item label="密码" prop="password">
                <el-input v-model="formLabelAlign.password" type="password" autocomplete="off" show-password></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="100">
            <el-col :span="9" :offset="2">
              <el-form-item label="用户昵称">
                <el-input v-model="formLabelAlign.name" disabled></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="9" :offset="1">
              <el-form-item label="性别">
                <el-select v-model="formLabelAlign.sex" placeholder="还没有性别" style="width: 100%;">
                  <el-option label="男" :value=1></el-option>
                  <el-option label="女" :value=2></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="100">
            <el-col :span="9" :offset="2">
              <el-form-item label="邮箱">
                <el-input v-model="formLabelAlign.email" disabled></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="9" :offset="1">
              <el-form-item label="账户验证状态">
                <el-input v-model="formLabelAlign.status" disabled></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row style="margin-top: 50px;">
            <el-col :span="5" :offset="9">
              <el-button type="primary" @click="submitForm('formLabelAlign')" style="width: 100%;">保存</el-button>
            </el-col>
          </el-row>
        </el-form>
      </el-card>

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
    </div>
</template>

<script>
    export default {
      name: "person-info",
      data(){
        let validatePass = (rule, value, callback) => {
          if (value === '') {
            callback(new Error('请输入密码'));
          } else {
            //6到30位数字、字母、特殊符号
            let passwordRex = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[~!@#$%^&*()_+`\-={}:";'<>?,.\/]).{6,30}$/;
            if (!passwordRex.test(this.formLabelAlign.password)) {
              callback(new Error('6到30位包含数字、字母、特殊符号'));
            }
            callback();
          }
        };
        return{
          formLabelAlign: {
            username: '',
            password: '',
            name: '',
            sex: 1,
            email: '',
            status: '',
            userId:'',
          },
          rules:{
            password: [
              { validator: validatePass, trigger: 'blur' }
            ]
          },
          dialogValue: '',
          centerDialogVisible: false,
        }
      },
      mounted() {
      //  使用生命周期和ajax加载数据
        let jsonObj = new Object();
        jsonObj.userId = window.sessionStorage.getItem("userId");
        let jsonMsg = JSON.stringify(jsonObj);
        let self = this;
        $.get("http://localhost:8083/user/getInfo.do",jsonObj,function (data) {
          self.formLabelAlign.username = data.username;
          if(data.sex === 1)
            self.formLabelAlign.sex = "男";
          else
            self.formLabelAlign.sex = "女";
          self.formLabelAlign.name = data.name;
          self.formLabelAlign.password = data.password;
          self.formLabelAlign.status = "已启用";
          self.formLabelAlign.email = data.email;
        },"json");
      },
      methods:{
        // submitForm(){
        //
        //   console.log(JSON.stringify(this.formLabelAlign));
        // }
        submitForm(formName) {
          this.$refs[formName].validate((valid) => {
            if (valid) {
              // alert('submit!');
              this.formLabelAlign.userId = window.sessionStorage.getItem("userId");
              let jsonMsg = JSON.stringify(this.formLabelAlign);
              let self = this;
              $.post("http://localhost:8083/user/setInfo.do",jsonMsg,function (data) {
                if(data.code === 1){
                  self.dialogValue = "保存成功";
                }else{
                  self.dialogValue = "保存失败，错误码：" + data.code;
                }
                self.centerDialogVisible = true;
              },"json");
            } else {
              // console.log('error submit!!');
              return false;
            }
          });
        },
        clickButton(){
          this.centerDialogVisible = false;
        }
      }
    }
</script>

<style scoped>
  .personInfo{
    width: 80%;
    margin: 0 auto;
    padding-top: 80px;
  }
</style>
