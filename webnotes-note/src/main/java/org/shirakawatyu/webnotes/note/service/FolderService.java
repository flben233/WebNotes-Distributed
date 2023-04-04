package org.shirakawatyu.webnotes.note.service;

import org.shirakawatyu.webnotes.common.R;
import org.shirakawatyu.webnotes.note.pojo.Folder;

import java.util.List;

public interface FolderService {
    R createFolder(String name);

    List<Folder> getAllFolder(String username);
    R deleteFolder(int id);

}
