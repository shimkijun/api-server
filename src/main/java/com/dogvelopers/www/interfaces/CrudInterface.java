package com.dogvelopers.www.interfaces;

import com.dogvelopers.www.model.network.Header;

public interface CrudInterface<Req,Res> {

    Header<Res> create(Req request);

    Header<Res> read(Long id);

    Header<Res> update(Req request);

    Header delete(Long id);
}
