package com.example.crimenotification.data.source.local

import com.example.crimenotification.room.dao.CriminalDao
import com.example.crimenotification.room.entity.CriminalEntity
import com.example.crimenotification.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CriminalLocalDataSourceImpl @Inject constructor(private val criminalDao: CriminalDao) :
    CriminalLocalDataSource {

    override suspend fun getLocalCriminals(): Result<List<CriminalEntity>> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                Result.Success(criminalDao.getAll())
            } catch (e: Exception) {
                Result.Error(Exception("Error getAllSSGEntity!"))
            }
        }

    override suspend fun registerCriminalEntity(entityList: List<CriminalEntity>): Boolean =
        withContext(Dispatchers.IO) {
            return@withContext try {
                registerAll(entityList)
            } catch (e: Exception) {
                false
            }
        }

    override suspend fun getCriminalEntity(name: String): Result<CriminalEntity> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                Result.Success(criminalDao.getCriminalEntity(name))
            } catch (e: Exception) {
                Result.Error(e)
            }
        }

    private fun registerAll(list: List<CriminalEntity>): Boolean {
        var isAllSave = true
        list.forEach {
            isAllSave = isAllSave.and(criminalDao.registerCriminalEntity(it) > 0)
        }
        return isAllSave
    }
}
