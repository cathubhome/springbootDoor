package com.ciwr.service.sys;

import com.ciwr.vo.WebUploadVo;

import java.io.File;

@SuppressWarnings("all")
public interface WebUploadService {

    Object check(WebUploadVo vo);

    Object chunkUpload(File file, WebUploadVo vo);

    Object unChunkUpload(File file, WebUploadVo vo);
}