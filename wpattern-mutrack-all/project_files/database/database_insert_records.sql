-- Password: "admin".
INSERT INTO `mutrack`.`tb_user` (`id`, `name`, `email`, `password`) VALUES ('1', 'admin', 'admin@admin.com', '495b52440d20bfe869f766524829e615d8eb9e331af57d1a151849eedd069adccdcd4c636d7fedd4');
-- Password: "user".
INSERT INTO `mutrack`.`tb_user` (`id`, `name`, `email`, `password`) VALUES ('2', 'user', 'user@user.com', 'e294fe74cbff685bf56c9d2e56e27a5952ba79e152a031284c6a74a793682abd91f63a3d5a88e5f2');

INSERT INTO `mutrack`.`tb_package` (`id`, `name`, `code`, `description`, `register_date`, `owner_id`) VALUES ('1', 'Sensor de Temperatura', 'RE736868622SE', 'Compra realizada na Aliexpress.', '2015-05-28', '1');
INSERT INTO `mutrack`.`tb_package` (`id`, `name`, `code`, `description`, `register_date`, `owner_id`) VALUES ('2', 'Livro Angularjs', 'DM567434841BR', 'Enviada pela submarino.', '2015-05-21', '2');
INSERT INTO `mutrack`.`tb_package` (`id`, `name`, `code`, `description`, `register_date`, `owner_id`) VALUES ('3', 'Arduino Leonardo', 'RJ367374538CN', 'Compra da DX', '2015-04-01', '1');

INSERT INTO `mutrack`.`tb_user_permission` (`permission_id`, `user_id`) VALUES ('1', '1');
INSERT INTO `mutrack`.`tb_user_permission` (`permission_id`, `user_id`) VALUES ('2', '1');
INSERT INTO `mutrack`.`tb_user_permission` (`permission_id`, `user_id`) VALUES ('2', '2');
