## 依赖
### 1.全局
```
    dependencies {
      ...
      classpath 'com.hujiang.aspectjx:gradle-android-plugin-aspectjx:2.0.0'
    }

    repositories {
      ...
      maven { url 'https://jitpack.io' }
    }

````
### 2.模块中
```
    dependencies{
      ...
      implementation '本项目'
    }
```

## 需要权限的函数
### 1.首先初始化

    @Override
    protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      GPermisson.init(new PermissionGlobalConfigCallback() {
        @Override
        public void shouldShowRational(String permission, int ration) {
            Log.e("momo","shouldShowRational"+permission+": "+ration);
        }


        @Override
        public void onPermissonReject(String permission, int reject) {
            Log.e("momo","onPermissonReject"+permission+": "+reject);
        }
      });
    }

### 2.注解使用
    @Permission({Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CAMERA})
    private void testPermission(){
        Log.e("momo","测试权限");
    }