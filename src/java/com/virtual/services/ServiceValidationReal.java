/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virtual.services;

import javax.jws.WebService;

/**
 *
 * @author Emerio
 */
@WebService
public interface ServiceValidationReal {
    String validation(String threadId, Integer noSimulasi, String username, String password, String noKartuKredit, String idBuku);
}
