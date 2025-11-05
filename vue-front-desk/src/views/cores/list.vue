<template>
  <div id="view-body" class="view-body view-body-blog">
    <div class="row">
      <div class="col-md-12 col-sm-12 ">
        <div class="search-container">
          <input 
            type="text" 
            class="search-input" 
            placeholder="搜索文章..." 
            v-model="query.keyword"
            @keyup.enter="init"
          >
          <button class="search-btn" @click="init">
            <i class="fa fa-search"></i>
          </button>
        </div>
        <div>
          <h2 style="text-align:center;padding-bottom: 30px">{{ this.$route.query.summaryName }}</h2>
        </div>
        <div class="row-content">
          <article-item :article-list="rowItem"></article-item>
          <article-page :current-page="query.currPage" :page-all="totalPage"
                        v-on:pageprocss="pageProcss"></article-page>
        </div>
      </div>
    </div>
    <blog-foot></blog-foot>
  </div>
</template>

<script>
import blogFoot from '@/views/components/blog-foot';
import articleItem from '@/views/components/article-item';
import articlePage from '@/views/components/article-page';
import {fetchList} from '@/api/article';

export default {
  name: 'list',
  components: {
    blogFoot: blogFoot,
    articleItem: articleItem,
    articlePage: articlePage
  },
  data() {
    return {
      query: {
        currPage: 1, // 当前页
        limit: 5, // 总页数
        summaryId: undefined,
        keyword: ''
      },
      totalPage: 0,
      rowItem: []
    }
  },
  watch:{
    $route() {
      if (this.$route.query.refresh === 'true'){
        this.init();
      }
    }
  },
  created() {
    this.init();
  },
  methods: {
    init() {
      if (this.$route.query.summaryId != null) {
        this.query.summaryId = this.$route.query.summaryId
      }
      fetchList(this.query).then(res => {
        this.totalPage = res.data.map.totalPage
        this.rowItem = res.data.map.items
      })
    },
    pageProcss(data) {
      this.query.currPage = data.current
      this.init();
      if (data.type == null) {
        alert(data.message);
      }
    }
  }
}
</script>

<style scoped>
.search-container {
  display: flex;
  margin-bottom: 20px;
  margin-top: 10px;
  max-width: 600px;
  margin-left: auto;
  margin-right: auto;
}

.search-input {
  flex: 1;
  padding: 12px 16px;
  border: 2px solid #e1e1e1;
  border-radius: 25px 0 0 25px;
  font-size: 16px;
  outline: none;
  transition: all 0.3s ease;
}

.search-input:focus {
  border-color: #409EFF;
  box-shadow: 0 0 0 2px rgba(64, 158, 255, 0.1);
}

.search-btn {
  padding: 12px 24px;
  border: 2px solid #409EFF;
  border-left: none;
  border-radius: 0 25px 25px 0;
  background-color: #409EFF;
  color: white;
  font-size: 16px;
  cursor: pointer;
  outline: none;
  transition: all 0.3s ease;
}

.search-btn:hover {
  background-color: #66B1FF;
  border-color: #66B1FF;
}

.moreTag img {
  width: 30px;
  transition: all;
  -webkit-transition: all .4s;
  -moz-transition: all .4s;
}

.row-content {
  margin-bottom: 6px;
}
</style>
