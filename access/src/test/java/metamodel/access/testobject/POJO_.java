
package metamodel.access.testobject;

import java.util.List;
import javax.annotation.Generated;
import metamodel.field.CollectionField;
import metamodel.field.SingularField;
import metamodel.field.impl.CollectionFieldImpl;
import metamodel.field.impl.SingularFieldImpl;
import metamodel.method.Method0;
import metamodel.method.Method1;
import metamodel.method.impl.Method0Impl;
import metamodel.method.impl.Method1Impl;


/**
 * @see POJO
 * 
 * 
 */
@Generated(value = "metamodel.generator.ModelFromSourceBuilder", date = "Sat Nov 29 12:39:24 CET 2014")
public abstract class POJO_ {

    /**
     * @see POJO#myint
     * 
     */
    public final static SingularField<POJO, java.lang.Integer> myint = new SingularFieldImpl<>("myint", POJO.class);
    /**
     * @see POJO#myInteger
     * 
     */
    public final static SingularField<POJO, Integer> myInteger = new SingularFieldImpl<>("myInteger", POJO.class);
    /**
     * @see POJO#rawList
     * 
     */
    public final static CollectionField<POJO, List, Object> rawList = new CollectionFieldImpl<>("rawList", POJO.class);
    /**
     * @see POJO#wildcardList
     * 
     */
    public final static CollectionField<POJO, List<?> , ?> wildcardList = new CollectionFieldImpl<>("wildcardList", POJO.class);
    /**
     * @see POJO#multidecl1
     * 
     */
    public final static SingularField<POJO, java.lang.Long> multidecl1 = new SingularFieldImpl<>("multidecl1", POJO.class);
    /**
     * @see POJO#multidecl2
     * 
     */
    public final static SingularField<POJO, java.lang.Long> multidecl2 = new SingularFieldImpl<>("multidecl2", POJO.class);
    /**
     * @see POJO#getMyint()
     * 
     */
    public final static Method0 <POJO, java.lang.Integer> getMyint = new Method0Impl<>("getMyint", POJO.class);
    /**
     * @see POJO#setMyint(int)
     * 
     */
    public final static Method1 <POJO, Void, java.lang.Integer> setMyint = new Method1Impl<>("setMyint", POJO.class, int.class);
    /**
     * @see POJO#getMyInteger()
     * 
     */
    public final static Method0 <POJO, Integer> getMyInteger = new Method0Impl<>("getMyInteger", POJO.class);
    /**
     * @see POJO#setMyInteger(Integer)
     * 
     */
    public final static Method1 <POJO, Void, Integer> setMyInteger = new Method1Impl<>("setMyInteger", POJO.class, Integer.class);
    /**
     * @see POJO#getRawList()
     * 
     */
    public final static Method0 <POJO, List> getRawList = new Method0Impl<>("getRawList", POJO.class);
    /**
     * @see POJO#setRawList(List)
     * 
     */
    public final static Method1 <POJO, Void, List> setRawList = new Method1Impl<>("setRawList", POJO.class, List.class);
    /**
     * @see POJO#getWildcardList()
     * 
     */
    public final static Method0 <POJO, List<?>> getWildcardList = new Method0Impl<>("getWildcardList", POJO.class);
    /**
     * @see POJO#setWildcardList(List<?>)
     * 
     */
    public final static Method1 <POJO, Void, List<?>> setWildcardList = new Method1Impl<>("setWildcardList", POJO.class, List.class);
    /**
     * @see POJO#methodWithPrimitiveArray(int[])
     * 
     */
    public final static Method1 <POJO, Void, int[]> methodWithPrimitiveArray = new Method1Impl<>("methodWithPrimitiveArray", POJO.class, int[].class);
    /**
     * @see POJO#methodWithObjectArray(Long[])
     * 
     */
    public final static Method1 <POJO, Void, Long[]> methodWithObjectArray = new Method1Impl<>("methodWithObjectArray", POJO.class, Long[].class);

}
