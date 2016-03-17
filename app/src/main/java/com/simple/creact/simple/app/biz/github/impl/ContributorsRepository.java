package com.simple.creact.simple.app.biz.github.impl;


import com.simple.creact.library.framework.datasource.DataSource;
import com.simple.creact.simple.app.biz.BaseObservableRepository;
import com.simple.creact.simple.app.data.di.common.QualifierConstants;
import com.simple.creact.simple.app.data.entity.github.Contributor;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author:YJJ
 * @date:2016/3/9
 * @email:yangjianjun@117go.com
 */
public class ContributorsRepository extends BaseObservableRepository<List<Contributor>,List<Contributor>> {

    @Inject
    public ContributorsRepository( @Named(QualifierConstants.PROVIDE_CONTRIBUTOR_DATA_SOURCE_RX)DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public List<Contributor> convert(List<Contributor> contributors) {
        return contributors;
    }
}
