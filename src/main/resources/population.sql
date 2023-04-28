--- Population for role table

INSERT INTO public.role(role_id, name) VALUES(0, 'SuperAdmin');
INSERT INTO public.role(role_id, name) VALUES(1, 'User');

--- Population for permission table

INSERT INTO public.permission(permission_id, name) VALUES(1, 'SuperAdmin Login');
INSERT INTO public.permission(permission_id, name) VALUES(2, 'SuperAdmin Logout');
INSERT INTO public.permission(permission_id, name) VALUES(3, 'SuperAdmin Delete User Data');
INSERT INTO public.permission(permission_id, name) VALUES(4, 'SuperAdmin Update User Data');
INSERT INTO public.permission(permission_id, name) VALUES(5, 'SuperAdmin Read User Data');
INSERT INTO public.permission(permission_id, name) VALUES(6, 'SuperAdmin Delete Post Data');
INSERT INTO public.permission(permission_id, name) VALUES(7, 'SuperAdmin Update Post Data');
INSERT INTO public.permission(permission_id, name) VALUES(8, 'SuperAdmin Read Post Data');
INSERT INTO public.permission(permission_id, name) VALUES(9, 'SuperAdmin Delete Review Data');
INSERT INTO public.permission(permission_id, name) VALUES(10, 'SuperAdmin Update Review Data');
INSERT INTO public.permission(permission_id, name) VALUES(11, 'SuperAdmin Read Review Data');
INSERT INTO public.permission(permission_id, name) VALUES(12, 'User Login');
INSERT INTO public.permission(permission_id, name) VALUES(13, 'User Logout');
INSERT INTO public.permission(permission_id, name) VALUES(14, 'User Signup');
INSERT INTO public.permission(permission_id, name) VALUES(15, 'User Create Post');
INSERT INTO public.permission(permission_id, name) VALUES(16, 'User Read Post');
INSERT INTO public.permission(permission_id, name) VALUES(17, 'User Update Post');
INSERT INTO public.permission(permission_id, name) VALUES(18, 'User Delete Post');
INSERT INTO public.permission(permission_id, name) VALUES(19, 'User Create Review');
INSERT INTO public.permission(permission_id, name) VALUES(20, 'User Read Review');
INSERT INTO public.permission(permission_id, name) VALUES(21, 'User Update Review');
INSERT INTO public.permission(permission_id, name) VALUES(22, 'User Delete Review');

--- Population for role_permission table

INSERT INTO public.role_permission(role_permission_id, role_id, permission_id) VALUES(1, 0, 1);
INSERT INTO public.role_permission(role_permission_id, role_id, permission_id) VALUES(2, 0, 2);
INSERT INTO public.role_permission(role_permission_id, role_id, permission_id) VALUES(3, 0, 3);
INSERT INTO public.role_permission(role_permission_id, role_id, permission_id) VALUES(4, 0, 4);
INSERT INTO public.role_permission(role_permission_id, role_id, permission_id) VALUES(5, 0, 5);
INSERT INTO public.role_permission(role_permission_id, role_id, permission_id) VALUES(6, 0, 6);
INSERT INTO public.role_permission(role_permission_id, role_id, permission_id) VALUES(7, 0, 7);
INSERT INTO public.role_permission(role_permission_id, role_id, permission_id) VALUES(8, 0, 8);
INSERT INTO public.role_permission(role_permission_id, role_id, permission_id) VALUES(9, 0, 9);
INSERT INTO public.role_permission(role_permission_id, role_id, permission_id) VALUES(10, 0, 10);
INSERT INTO public.role_permission(role_permission_id, role_id, permission_id) VALUES(11, 0, 11);
INSERT INTO public.role_permission(role_permission_id, role_id, permission_id) VALUES(12, 0, 12);
INSERT INTO public.role_permission(role_permission_id, role_id, permission_id) VALUES(13, 0, 13);
INSERT INTO public.role_permission(role_permission_id, role_id, permission_id) VALUES(14, 0, 14);
INSERT INTO public.role_permission(role_permission_id, role_id, permission_id) VALUES(15, 0, 15);
INSERT INTO public.role_permission(role_permission_id, role_id, permission_id) VALUES(16, 0, 16);
INSERT INTO public.role_permission(role_permission_id, role_id, permission_id) VALUES(17, 0, 17);
INSERT INTO public.role_permission(role_permission_id, role_id, permission_id) VALUES(18, 0, 18);
INSERT INTO public.role_permission(role_permission_id, role_id, permission_id) VALUES(19, 0, 19);
INSERT INTO public.role_permission(role_permission_id, role_id, permission_id) VALUES(20, 0, 20);
INSERT INTO public.role_permission(role_permission_id, role_id, permission_id) VALUES(21, 0, 21);
INSERT INTO public.role_permission(role_permission_id, role_id, permission_id) VALUES(22, 0, 22);
INSERT INTO public.role_permission(role_permission_id, role_id, permission_id) VALUES(23, 1, 12);
INSERT INTO public.role_permission(role_permission_id, role_id, permission_id) VALUES(24, 1, 13);
INSERT INTO public.role_permission(role_permission_id, role_id, permission_id) VALUES(25, 1, 14);
INSERT INTO public.role_permission(role_permission_id, role_id, permission_id) VALUES(26, 1, 15);
INSERT INTO public.role_permission(role_permission_id, role_id, permission_id) VALUES(27, 1, 16);
INSERT INTO public.role_permission(role_permission_id, role_id, permission_id) VALUES(28, 1, 17);
INSERT INTO public.role_permission(role_permission_id, role_id, permission_id) VALUES(29, 1, 18);
INSERT INTO public.role_permission(role_permission_id, role_id, permission_id) VALUES(30, 1, 19);
INSERT INTO public.role_permission(role_permission_id, role_id, permission_id) VALUES(31, 1, 20);
INSERT INTO public.role_permission(role_permission_id, role_id, permission_id) VALUES(32, 1, 21);
INSERT INTO public.role_permission(role_permission_id, role_id, permission_id) VALUES(33, 1, 22);

--- Population for users table

--- Password: 123456
INSERT INTO public.users(user_id, name, surname, email, password, role_id, creation_date, update_date, pp_path, is_deleted) VALUES (
 1,
 'admin',
 'admin',
 'admin@gmail.com', 
 crypt('123456', gen_salt('bf')),
 0,
 now(),
 now(),
 null,
 false
);

--- Password: 123456
INSERT INTO public.users(user_id, name, surname, email, password, role_id, creation_date, update_date, pp_path, is_deleted) VALUES (
 2,
 'user',
 'user',
 'user@gmail.com', 
 crypt('123456', gen_salt('bf')),
 1,
 now(),
 now(),
 null,
 false
);

--- Population for category table

INSERT INTO public.category(category_id, category_name) VALUES(1, 'Vehicles');
INSERT INTO public.category(category_id, category_name) VALUES(2, 'Sports and Hobby');
INSERT INTO public.category(category_id, category_name) VALUES(3, 'Electronics');
INSERT INTO public.category(category_id, category_name) VALUES(4, 'Personal Goods');

--- Population for sub_category table

INSERT INTO public.sub_category(subcategory_id, subcategory_name, category_id) VALUES(1, 'Auto Accessories', 1);
INSERT INTO public.sub_category(subcategory_id, subcategory_name, category_id) VALUES(2, 'Bicycle', 1);
INSERT INTO public.sub_category(subcategory_id, subcategory_name, category_id) VALUES(3, 'Bicycle Accesories', 1);
INSERT INTO public.sub_category(subcategory_id, subcategory_name, category_id) VALUES(4, 'Motorbike Accessories', 1);
INSERT INTO public.sub_category(subcategory_id, subcategory_name, category_id) VALUES(5, 'Animal Accessories', 2);
INSERT INTO public.sub_category(subcategory_id, subcategory_name, category_id) VALUES(6, 'Music and Movies', 2);
INSERT INTO public.sub_category(subcategory_id, subcategory_name, category_id) VALUES(7, 'Sports Accessories', 2);
INSERT INTO public.sub_category(subcategory_id, subcategory_name, category_id) VALUES(8, 'Collectibles', 2);
INSERT INTO public.sub_category(subcategory_id, subcategory_name, category_id) VALUES(9, 'Musical Instruments', 2);
INSERT INTO public.sub_category(subcategory_id, subcategory_name, category_id) VALUES(10, 'Books/Magazines/Comics', 2);
INSERT INTO public.sub_category(subcategory_id, subcategory_name, category_id) VALUES(11, 'Hiking and Camping Accessories', 2);
INSERT INTO public.sub_category(subcategory_id, subcategory_name, category_id) VALUES(12, 'Others', 2);
INSERT INTO public.sub_category(subcategory_id, subcategory_name, category_id) VALUES(13, 'Computer Technologies', 3);
INSERT INTO public.sub_category(subcategory_id, subcategory_name, category_id) VALUES(14, 'Gaming Console and Video Games', 3);
INSERT INTO public.sub_category(subcategory_id, subcategory_name, category_id) VALUES(15, 'Photography', 3);
INSERT INTO public.sub_category(subcategory_id, subcategory_name, category_id) VALUES(16, 'Phones/Smart Phones', 3);
INSERT INTO public.sub_category(subcategory_id, subcategory_name, category_id) VALUES(17, 'Audio and Video', 3);
INSERT INTO public.sub_category(subcategory_id, subcategory_name, category_id) VALUES(18, 'Computer Accessories', 3);
INSERT INTO public.sub_category(subcategory_id, subcategory_name, category_id) VALUES(19, 'Photography Accessories', 3);
INSERT INTO public.sub_category(subcategory_id, subcategory_name, category_id) VALUES(20, 'Telephone Accessories', 3);
INSERT INTO public.sub_category(subcategory_id, subcategory_name, category_id) VALUES(21, 'Furniture and Household Goods', 4);
INSERT INTO public.sub_category(subcategory_id, subcategory_name, category_id) VALUES(22, 'Home Appliances', 4);
INSERT INTO public.sub_category(subcategory_id, subcategory_name, category_id) VALUES(23, 'Gardening Tools and Accessories', 4);
INSERT INTO public.sub_category(subcategory_id, subcategory_name, category_id) VALUES(24, 'Clothing and Accessories', 4);
INSERT INTO public.sub_category(subcategory_id, subcategory_name, category_id) VALUES(25, 'Goods for Babies and Children', 4);