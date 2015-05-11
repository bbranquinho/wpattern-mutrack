package org.wpattern.mutrack.data;

import org.springframework.stereotype.Component;
import org.wpattern.mutrack.data.utils.GenericData;
import org.wpattern.mutrack.utils.data.IUserData;
import org.wpattern.mutrack.utils.entities.UserEntity;

@Component
public class UserData extends GenericData<UserEntity, Long> implements IUserData {

}
