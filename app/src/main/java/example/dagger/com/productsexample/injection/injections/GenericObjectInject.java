package example.dagger.com.productsexample.injection.injections;

/**
 * Created by nelson336 on 02/08/16.
 */
public class GenericObjectInject<T>  {
    private T mT;

    public void set(T t) {
        mT = t;
    }

    public T get(){
        return mT;
    }

    public  static  <Y> Y parse(GenericObjectInject inject){
        try{
            return ((Y) inject.get());
        }catch (Exception e){
            return null;
        }
    }
}
