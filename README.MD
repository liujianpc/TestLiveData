### ViewModel学习demo
1. 构建ViewModel(LiveData是ViewModel的一个成员变量)
2. 构建观察者Observer绑定LifeCycle，且观察ViewModel的LiveData
3. LiveData的map转换
4. LiveData的SwitchMap转换
5. 多个LiveData混合MediatorLiveData驱动一个UI改变
6. 观察者模式，无需关心生命周期，ViewModel的LiveData的改变去驱动UI的刷新
7. 没有内存泄漏
8. 代码优雅和逻辑解耦
9. LiveData的改变方式：
   >主线程setValue()  
   子线程postValue()