package com.rupak.sample.ui.converter;

import com.rupak.sample.platform.common.UIModelConverter;
import com.rupak.sample.service.domains.UserDomain;
import com.rupak.sample.ui.model.UserModel;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;

/**
 *
 * @author rupak
 */
@Named
public class UserModelConverter implements UIModelConverter<UserModel, UserDomain> {

    @Override
    public UserModel toUIModel(UserDomain domainObject) {
        UserModel userModel = new UserModel();
        userModel.setName(domainObject.getName());
        userModel.setUserId(domainObject.getUserId());
        userModel.setPassword(domainObject.getPassword());
        userModel.setConfirmPassword(domainObject.getConfirmPassword());
        return userModel;
    }

    @Override
    public UserDomain toDomainObject(UserModel uiModel) {
        UserDomain userDomain = new UserDomain();
        userDomain.setName(uiModel.getName());
        userDomain.setUserId(uiModel.getUserId());
        userDomain.setPassword(uiModel.getPassword());
        userDomain.setConfirmPassword(uiModel.getConfirmPassword());
        return userDomain;
    }

    @Override
    public List<UserModel> toUIModelList(List<UserDomain> domainObjectList) {
        List<UserModel> userModels = new ArrayList<>();
        domainObjectList.forEach((userDomain) -> {
           userModels.add(this.toUIModel(userDomain));
        });
        return userModels;
    }

}
