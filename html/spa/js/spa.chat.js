/**
 * @author Mikko Kupsu
 */

/*jslint
  browser : true,    continue : true,    devel : true,
  indent : 2,        maxerr : 50,        newcap : true,
  nomen : true,      plusplus : true,    regexp : true,
  sloppy : true,     vars : false,        white : true
*/
/*global $, spa */

spa.chat = (function () {
  // ---- BEGIN MODULE SCOPE VARIABLES ----
  var configMap = {
    main_html : String()
      + '<div style="padding:1em; color:#fff;">'
        + 'Say hello to caht'
      + '</div>',
      settable_map : {}
    },
    stateMap = { $container : null },
    jqueryMap = {},
  
  setJqueryMap, configModule, initModule;
  // ---- END MODULE SCOPE VARIABLES ----
  
  // ---- BEGIN UTILITY METHODS ----
  // ---- END UTILITY METHODS ----
  
  // ---- BEGIN DOM METHODS ----
  // Begin DOM method /setJqueryMap/
  setJqueryMap = function () {
    var $container = stateMap.$container;
    jqueryMap = { $container : $container };
  };
  // End DOM method /setJqueryMap/
  // ---- END DOM METHODS ----
  
  // ---- BEGIN UTILITY METHODS ----
  // ---- END UTILITY METHODS ----
  
  // ---- BEGIN PUBLIC METHODS ----
  // Begin PUBLIC method /configModule/
  /**
   * Configure chat module.
   * 
   * @param map input_map
   * 
   * @settings @see configMap.settable_map
   * 
   * @return true
   */
  configModule = function ( input_map ) {
    spa.util.setConfigMap({
      input_map:      input_map,
      settable_map:   configMap.settble_map,
      configMap:      configMap
    });
    return true;
  };
  // End PUBLIC method /configModule/

  // Begin PUBLIC method /initModule/
  /**
   * Initialize module.
   * 
   * @param {Object} $container
   * 
   * @return true
   */
  initModule = function ( $container ) {
    $container.html( configMap.main_html );
    stateMap.$container = $container;
    setJqueryMap();
    return true;
  };  
  // End PUBLIC method /initModule/
  
  // return public methods
  return {
    configModule:   configModule,
    initModule:     initModule
  };
  // ---- END PUBLIC  METHODS ----
}());
