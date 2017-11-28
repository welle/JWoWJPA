package aka.jwowjpa.persistence.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-11-28T08:59:34.648+0100")
@StaticMetamodel(Item.class)
public class Item_ {
	public static volatile SingularAttribute<Item, Long> id;
	public static volatile SingularAttribute<Item, byte[]> icon;
	public static volatile SingularAttribute<Item, Long> idWoW;
	public static volatile SingularAttribute<Item, String> name;
	public static volatile SingularAttribute<Item, String> nameEN;
	public static volatile SingularAttribute<Item, String> quality;
	public static volatile SingularAttribute<Item, String> sourceId;
	public static volatile SingularAttribute<Item, String> sourceType;
	public static volatile ListAttribute<Item, Spell> spells;
	public static volatile ListAttribute<Item, Mount> mounts;
}
