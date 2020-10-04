/**
 * 
 */
package edu.saby.msec.catalogserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import edu.saby.msec.infra.config.AsynchronousResponseConfig;

/**
 * @author abhisheksa
 *
 */
@Configuration
@Import(AsynchronousResponseConfig.class)
public class CatalogServerConfig {

}
