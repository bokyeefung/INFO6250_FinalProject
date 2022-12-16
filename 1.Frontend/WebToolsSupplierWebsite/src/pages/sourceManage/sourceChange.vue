<template>
  <div id="source-home-container" class="source-home-container">

    <div style="background-color: white; border-bottom: 1px solid #eee;">
      <el-page-header style="padding: 24px; margin: 0 auto;"
                      @back="goBack" content="修改货源"></el-page-header>
    </div>


    <el-card style="margin: 20px;">
      <el-form :model="ruleForm" :rules="rules" :ref="ruleForm" label-width="100px" span="11"><!--:inline="true" -->
        <el-row>
          <el-col :xs="24" :sm="24" :md="12" :lg="12" :xl="12">
            <el-form-item label="货源名称" prop="sName">
              <el-input v-model="ruleForm.sName"></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="12" :lg="12" :xl="12">
            <el-form-item label="货源简介" prop="supplyIntroduction">
              <el-input v-model="ruleForm.supplyIntroduction"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :xs="24" :sm="24" :md="12" :lg="12" :xl="12">
            <el-form-item label="库存数量" prop="stockAmount">
              <el-input-number v-model="ruleForm.stockAmount" :min="0"></el-input-number>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="12" :lg="12" :xl="12">
            <el-form-item label="已售数量" prop="selledAmount">
              <el-input-number v-model="ruleForm.selledAmount" :min="0"></el-input-number>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :xs="24" :sm="24" :md="12" :lg="12" :xl="12">
            <el-form-item label="销售价格" prop="sellPrice">
              <el-input-number v-model="ruleForm.sellPrice" :min="0" :precision="2"></el-input-number>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="12" :lg="12" :xl="12">
            <el-form-item label="最少购买数量" prop="minimumQuantity">
              <el-input-number v-model="ruleForm.minimumQuantity" :min="0"></el-input-number>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :xs="24" :sm="24" :md="12" :lg="12" :xl="12">
            <el-form-item label="是否包邮" prop="mailStatus">
              <el-switch v-model="ruleForm.mailStatus"></el-switch>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="12" :lg="12" :xl="12">
            <el-form-item label="邮费" prop="postage">
              <el-input-number v-model="ruleForm.postage" :min="0" :precision="2"></el-input-number>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :xs="24" :sm="24" :md="12" :lg="12" :xl="12">
            <el-form-item label="同城发货" prop="cityAgent">
              <el-select v-model="ruleForm.cityAgent" placeholder="是否支持由同城市代理商发货">
                <el-option label="支持" value="1"></el-option>
                <el-option label="不支持" value="2"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="12" :lg="12" :xl="12">
            <el-form-item label="全国发货" prop="allAgent">
              <el-select v-model="ruleForm.allAgent" placeholder="是否支持由全国代理商发货">
                <el-option label="支持" value="1"></el-option>
                <el-option label="不支持" value="2"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :xs="24" :sm="24" :md="12" :lg="12" :xl="12">
            <el-form-item label="过期时间" prop="expirationTime">
              <el-date-picker type="date" placeholder="请选择过期日期" v-model="ruleForm.expirationTime"
                              style="width: 100%;"></el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="12" :lg="12" :xl="12">
            <el-form-item label="状态" prop="supplyStatus">
              <el-select v-model="ruleForm.supplyStatus" placeholder="请选择货源状态">
                <el-option label="正常" value="1"></el-option>
                <el-option label="缺货" value="2"></el-option>
                <el-option label="暂停" value="3"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>


        <el-form-item label="货源详情" prop="supplyDetails">
          <el-input v-model="ruleForm.supplyDetails" type="textarea"></el-input>
        </el-form-item>

        <el-form-item>
          <el-button type="primary">立即创建</el-button>
          <el-button>取消</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>


<script>
  export default {
    data() {
      return {
        numberValidateForm: {
          age: ''
        },
        ruleForm: {
          sName: '',
          imageUrl: '',
          supplyIntroduction: '',
          stockAmount: '',
          selledAmount: '',
          sellPrice: '',
          minimumQuantity: '',
          expirationTime: '',
          mailStatus: '',
          postage: '',
          cityAgent: '',
          allAgent: '',
          certificateUrl: '',
          supplyDetails: '',
          supplyStatus: ''
        },
        rules: {
          sName: [
            {required: true, message: '请输入货源名称', trigger: 'blur'},
            {min: 1, max: 10, message: '长度在 1 到 10 个字符', trigger: 'blur'}
          ],
          supplyIntroduction: [
            {required: true, message: '请输入货源简介', trigger: 'blur'},
            {min: 1, max: 100, message: '长度在 1 到 100 个字符', trigger: 'blur'}
          ],
          supplyDetails: [
            {required: true, message: '请输入货源详情', trigger: 'blur'},
            {min: 1, max: 1000, message: '长度在 1 到 1000 个字符', trigger: 'blur'}
          ],
          mailStatus: [
            {required: true, trigger: 'blur'}
          ],
          cityAgent: [
            {required: true, trigger: 'blur'}
          ],
          allAgent: [
            {required: true, trigger: 'blur'}
          ],
          supplyStatus: [
            {required: true, trigger: 'blur'}
          ],
          expirationTime: [
            { type: 'date', required: true, message: '请选择日期', trigger: 'blur' }
          ],
          type: [
            { type: 'array', required: true, message: '请至少选择一个活动性质', trigger: 'change' }
          ],
          resource: [
            { required: true, message: '请选择活动资源', trigger: 'change' }
          ],
          desc: [
            { required: true, message: '请填写活动形式', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            alert('submit!');
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
      goBack() {
        let that = this;
        that.$router.push('/source');
      }
    }
  }
</script>

<style lang="scss" scoped>

  .source-home-container {
  }

</style>

