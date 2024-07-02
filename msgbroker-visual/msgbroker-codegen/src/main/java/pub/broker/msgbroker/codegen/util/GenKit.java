package pub.broker.msgbroker.codegen.util;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import pub.broker.msgbroker.codegen.entity.GenDatasourceConf;
import pub.broker.msgbroker.codegen.mapper.GenDatasourceConfMapper;
import pub.broker.msgbroker.codegen.mapper.GeneratorMapper;
import pub.broker.msgbroker.common.core.util.SpringContextHolder;
import pub.broker.msgbroker.common.datasource.enums.DsJdbcUrlEnum;
import lombok.experimental.UtilityClass;
import org.springframework.context.ApplicationContext;

import java.util.Map;

/**
 * 代码生成工具类
 *
 * @author broker.pub
 * @date 2023/2/16
 */
@UtilityClass
public class GenKit {

	/**
	 * 获取功能名 sys_a_b sysAb
	 * @param tableName 表名
	 * @return 功能名
	 */
	public String getFunctionName(String tableName) {
		return StrUtil.toCamelCase(tableName);
	}

	/**
	 * 获取模块名称
	 * @param packageName 包名
	 * @return 功能名
	 */
	public String getModuleName(String packageName) {
		return StrUtil.subAfter(packageName, ".", true);
	}

	/**
	 * 获取数据源对应方言的mapper
	 * @param dsName 数据源名称
	 * @return GeneratorMapper
	 */
	public GeneratorMapper getMapper(String dsName) {
		// 获取目标数据源数据库类型
		GenDatasourceConfMapper datasourceConfMapper = SpringContextHolder.getBean(GenDatasourceConfMapper.class);
		GenDatasourceConf datasourceConf = datasourceConfMapper
			.selectOne(Wrappers.<GenDatasourceConf>lambdaQuery().eq(GenDatasourceConf::getName, dsName));

		String dbConfType;
		// 默认MYSQL 数据源
		if (datasourceConf == null) {
			dbConfType = DsJdbcUrlEnum.MYSQL.getDbName();
		}
		else {
			dbConfType = datasourceConf.getDsType();
		}
		// 获取全部数据实现
		ApplicationContext context = SpringContextHolder.getApplicationContext();
		Map<String, GeneratorMapper> beansOfType = context.getBeansOfType(GeneratorMapper.class);

		// 根据数据类型选择mapper
		for (String key : beansOfType.keySet()) {
			if (StrUtil.containsIgnoreCase(key, dbConfType)) {
				return beansOfType.get(key);
			}
		}

		throw new IllegalArgumentException("dsName 不合法: " + dsName);
	}

}
