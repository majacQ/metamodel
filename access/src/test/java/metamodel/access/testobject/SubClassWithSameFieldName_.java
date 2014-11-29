
package metamodel.access.testobject;

import javax.annotation.Generated;
import metamodel.field.SingularField;
import metamodel.field.impl.SingularFieldImpl;
import metamodel.method.Method0;
import metamodel.method.Method1;
import metamodel.method.impl.Method0Impl;
import metamodel.method.impl.Method1Impl;


/**
 * @see SubClassWithSameFieldName
 * 
 * 
 */
@Generated(value = "metamodel.generator.ModelFromSourceBuilder", date = "Sat Nov 29 12:39:24 CET 2014")
public abstract class SubClassWithSameFieldName_
    extends POJO_
{

    /**
     * @see SubClassWithSameFieldName#myint
     * 
     */
    public final static SingularField<SubClassWithSameFieldName, Integer> myint = new SingularFieldImpl<>("myint", SubClassWithSameFieldName.class);
    /**
     * @see SubClassWithSameFieldName#subString
     * 
     */
    public final static SingularField<SubClassWithSameFieldName, String> subString = new SingularFieldImpl<>("subString", SubClassWithSameFieldName.class);
    /**
     * @see SubClassWithSameFieldName#sub_getMyint()
     * 
     */
    public final static Method0 <SubClassWithSameFieldName, Integer> sub_getMyint = new Method0Impl<>("sub_getMyint", SubClassWithSameFieldName.class);
    /**
     * @see SubClassWithSameFieldName#sub_setMyint(int)
     * 
     */
    public final static Method1 <SubClassWithSameFieldName, Void, Integer> sub_setMyint = new Method1Impl<>("sub_setMyint", SubClassWithSameFieldName.class, int.class);
    /**
     * @see SubClassWithSameFieldName#getSubString()
     * 
     */
    public final static Method0 <SubClassWithSameFieldName, String> getSubString = new Method0Impl<>("getSubString", SubClassWithSameFieldName.class);
    /**
     * @see SubClassWithSameFieldName#setSubString(String)
     * 
     */
    public final static Method1 <SubClassWithSameFieldName, Void, String> setSubString = new Method1Impl<>("setSubString", SubClassWithSameFieldName.class, String.class);

}
