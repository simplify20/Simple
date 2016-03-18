**Simple**
----------
####**Simple**是一个基于**MVVM**架构的简单且清晰的安卓应用框架。

#### 编写的Simple的目的有以下几点：

- 提高代码的可测性；
- 减少MVP架构中View层和Presenter之间的回调；
- 降低层级之间的耦合；
- 降低对第三方库的依赖以及第三方库代码的侵入性；
- 使依赖对上层透明，依赖可注入

####**Simple的特性**
- 低侵入性。框架层未引用任何第三方库或Android API，全部基于JDK开发，通过抽象进行依赖，低层对高层透明；
- 可测性。Model-ViewModel-View之间层清晰，耦合性很低，除View层以外，其他层代码未使用Android API,可测性和可移植性都很高；
- Model层采用了Repository设计模式，扩展性很好，接口很干净；
- 应用层采用了DataBinding框架，data可直接绑定到视图上，减少了模板代码；
- 采用Dagger2作为依赖注入框架，对象的创建，依赖的管理更加灵活。
- 采用RxJava处理异步任务；
- 大量采用设计模式优化架构。

####**Simple架构简图**

![arch (1).png](http://upload-images.jianshu.io/upload_images/1763559-4fcb4e0fb20a8b37.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

